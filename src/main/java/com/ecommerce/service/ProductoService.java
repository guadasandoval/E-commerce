package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Categoria;
import com.ecommerce.model.Producto;
import com.ecommerce.repository.ProductoRepo;

//se implementan los métodos del repositorio

@Service
@Transactional
public class ProductoService {

	@Autowired
	ProductoRepo producRepo;

	public List<Producto> obtenerTodos() {
		List<Producto> lista = producRepo.findAll();
		return lista;
	}

	public Optional<Producto> obtenerPorId(Integer id) {
		return producRepo.findById(id);
	}

	public Optional<Producto> obtenerPorNombre(String nombre) {
		return producRepo.findByNombre(nombre);
	}
	
	public List<Producto> obtenerPorCategoria(Categoria categoria){
		return producRepo.findByCategoria(categoria);
	}

	public void guardar(Producto producto) {
		producRepo.save(producto);
	}

	public void borrar(Integer id) {
		producRepo.deleteById(id);
	}

	public boolean existeNombre(String nombre) {
		return producRepo.existsByNombre(nombre);
	}
	
	 public  boolean existePorId(Integer id){
	        return producRepo.existsById(id);
	    }

	// comprobar el stock
		 public boolean sinStock(Integer id) {
			Producto prodStock = producRepo.findById(id).get();
			
	        return prodStock.getStock() <= 0;
	    }
		 
			public List<Producto> obtenerCarrito() {
		        
				List<Producto> carrito = new ArrayList<Producto>();
			       
				return carrito;
			    }
}
