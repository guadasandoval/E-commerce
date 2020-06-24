package com.ecommerce.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.SinStockException;
import com.ecommerce.form.DetalleForm;
import com.ecommerce.form.VentaForm;
import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Producto;
import com.ecommerce.model.Usuario;
import com.ecommerce.model.Venta;
import com.ecommerce.service.DetalleVentaService;

import com.ecommerce.service.ProductoService;
import com.ecommerce.service.UsuarioService;
import com.ecommerce.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;

@RestController
@RequestMapping(path = "/v1")
public class VentaController {

	@Autowired
	VentaService ventaService;

	@Autowired
	ProductoService prodService;

	@Autowired
	DetalleVentaService detalleService;

	@Autowired
	UsuarioService usuarioService;

	@GetMapping(path = "/venta")
	public ResponseEntity<List<Venta>> listarVentas() {
		List<Venta> listaVentas = ventaService.listaVentas();

		return new ResponseEntity<>(listaVentas, HttpStatus.OK);
	}

	@PostMapping(path = "/venta")
	@Operation(summary = "Guarda", description = "guardar productos")
	public ResponseEntity<Venta> crearVenta(@RequestBody VentaForm ventaForm)
			throws NotFoundException, SinStockException {

		Venta venta = new Venta();
		ventaService.crearVenta(venta);
		Usuario usuarioVenta = usuarioService.obtenerPorId(ventaForm.getIdUsuario()).get();

		Date fechaActual = new Date();

		venta.setIdUser(usuarioVenta);
		venta.setFecha(fechaActual);

		Set<DetalleVenta> detalleVenta = new HashSet<DetalleVenta>();

		for (DetalleForm detalleForm : ventaForm.getDetalleForm()) {

			int idProd = detalleForm.getIdProd();
			int cantidadProd = detalleForm.getCantidadProd();

			Producto prod = prodService.obtenerPorId(idProd).get();

			if (prod == null) {
				throw new NotFoundException("El id ingresado no existe");
			}

			if (prodService.sinStock(prod.getId())) {

				throw new SinStockException("Sin stock");
			}

			else {

				DetalleVenta prodDetalle = new DetalleVenta();

				prodDetalle.setVenta(venta);
				prodDetalle.setProd(prod);
				prodDetalle.setCantidad(cantidadProd);
				prodDetalle.setPrecio(prod.getPrecio());
				detalleService.actualizarStock(prodDetalle);

				detalleVenta.add(prodDetalle);
				

			}
			venta.setDetalleVenta(detalleVenta);

		}

		
		return new ResponseEntity<>(venta, HttpStatus.OK);
	}

	@GetMapping(path = "/venta/{id}")
	@Operation(summary = "Ventas por usuario", description = "Listado de ventas por usuario")
	public ResponseEntity<Venta> listaVentaUsuario(@PathVariable Integer id) {

		if (usuarioService.existePorId(id)) {
			Usuario user = usuarioService.obtenerPorId(id).get();
			List<Venta> ventaByUser = ventaService.obtenerPorUsuario(user);

			return new ResponseEntity(ventaByUser, HttpStatus.OK);

		}
		return new ResponseEntity("El id ingresado no existe", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(path = "/venta/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Venta> borrarVenta(@PathVariable Integer id) {

		ventaService.borrar(id);
		// crea un objeto response entity vacio, sin contenido
		return ResponseEntity.noContent().build();
	}
}
