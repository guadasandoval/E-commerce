package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.form.ProductoForm;
import com.ecommerce.model.Categoria;
import com.ecommerce.model.Producto;
import com.ecommerce.repository.CategoriaRepo;

import com.ecommerce.service.ProductoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
public class ProductoController {

	@Autowired
	ProductoService prodService;

	@Autowired
	CategoriaRepo categoriaRepo;

	@GetMapping(path = "/productos")
	
	@Operation(summary = "Lista de productos", description = "Obtener lista de productos")
	public ResponseEntity<List<Producto>> listarProductos() {

		List<Producto> lista = prodService.obtenerTodos();

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping(path = "/producto/{id}")
	@Operation(summary = "Producto id", description = "Obtener productos por id")
	public ResponseEntity<Producto> buscarPorId(@PathVariable Integer id) {

		if (prodService.existePorId(id)) {

			Producto prod = prodService.obtenerPorId(id).get();

			return new ResponseEntity<>(prod, HttpStatus.OK);
		}
		return new ResponseEntity("El id ingresado no existe", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "/producto")

	@Operation(summary = "Cargar", description = "cargar productos")
	public ResponseEntity<Producto> guardarProd(@RequestBody ProductoForm prod) {

		if (prod.getNombre().isBlank()) {
			return new ResponseEntity("El nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
		}

		if (prodService.existeNombre(prod.getNombre())) {
			return new ResponseEntity("Ese nombre ya existe", HttpStatus.BAD_REQUEST);
		}

		if (prod.getCategoria() == null) {
			return new ResponseEntity("Debe asignar una categoria al producto", HttpStatus.BAD_REQUEST);
		}

		if (prod.getPrecio() < 1) {
			return new ResponseEntity("El precio debe ser mayor que 0", HttpStatus.BAD_REQUEST);

		}

		Producto nuevoProd = new Producto();
		nuevoProd.setNombre(prod.getNombre());

		Categoria cat = categoriaRepo.findByNombre(prod.getCategoria()).get();

		nuevoProd.setCategoria(cat);
		nuevoProd.setCodigo(prod.getCodigo());
		nuevoProd.setStock(prod.getStock());
		nuevoProd.setPrecio(prod.getCodigo());

		prodService.guardar(nuevoProd);
		return new ResponseEntity<Producto>(nuevoProd, HttpStatus.CREATED);
	}

	@PutMapping(path = "/producto/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Actualizar", description = "Actualiza datos de productos")
	public ResponseEntity<Producto> actualizarProducto(@RequestBody ProductoForm prod, @PathVariable Integer id)
			throws NotFoundException {

		if (!prodService.existePorId(id)) {
			return new ResponseEntity("El producto ingresado no existe", HttpStatus.NOT_FOUND);
		}

		if (prod.getNombre().isBlank()) {
			return new ResponseEntity("El nombre es obligatorio", HttpStatus.BAD_REQUEST);
		}
		if ((Integer) prod.getPrecio() == null || prod.getPrecio() == 0) {
			return new ResponseEntity("El precio es obligatorio", HttpStatus.BAD_REQUEST);
		}
		if (

		prodService.obtenerPorNombre(prod.getNombre()).get().getId() != id) {
			return new ResponseEntity("Ese nombre ya existe", HttpStatus.BAD_REQUEST);
		}

		Producto prodUpdate = prodService.obtenerPorId(id).get();
		prodUpdate.setNombre(prod.getNombre());
		prodUpdate.setPrecio(prod.getPrecio());

		Categoria cat = categoriaRepo.findByNombre(prod.getCategoria()).get();
		prodUpdate.setCategoria(cat);
		prodUpdate.setCodigo(prod.getCodigo());
		prodUpdate.setStock(prod.getStock());
		prodService.guardar(prodUpdate);

		return new ResponseEntity("Producto actualizado", HttpStatus.OK);

	}

	@DeleteMapping(path = "/producto/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Producto> borrarProducto(@PathVariable Integer id) {

		if (prodService.existePorId(id)) {

			prodService.borrar(id);
			// crea un objeto response entity vacio, sin contenido
			return ResponseEntity.noContent().build();
		}

		return new ResponseEntity("El id ingresado no existe", HttpStatus.BAD_REQUEST);

	}

	@GetMapping(path = "/search/{nombre}")
	@Operation(summary = "Catalogo", description = "Obtener lista de productos por categorias")
	public ResponseEntity<List<Producto>> listarPorCategoria(@PathVariable String nombre) {

		Categoria categoria = categoriaRepo.findByNombre(nombre).get();

		List<Producto> listaCategoria = prodService.obtenerPorCategoria(categoria);

		return new ResponseEntity(listaCategoria, HttpStatus.OK);
	}

}
