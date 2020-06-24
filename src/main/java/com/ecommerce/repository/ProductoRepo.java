package com.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Categoria;
import com.ecommerce.model.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer>{
	
	
	public Optional<Producto> findByNombre(String nombre);
	
	public List<Producto> findByCategoria(Categoria categoria);
	
	// comprobar antes de insertar un nuevo registro si el nombre del producto existe 
	boolean existsByNombre (String nombre);
	
	boolean existsById(Integer id);


}
