package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Categoria;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

	public Optional<Categoria> findByNombre(String nombre);
	public Optional<Categoria> findById(Integer id);

	boolean existsByNombre(String nombre);

	boolean existsById(Integer id);
}
