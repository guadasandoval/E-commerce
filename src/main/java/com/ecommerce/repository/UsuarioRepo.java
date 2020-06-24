package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Usuario;

// interfaz que se encargara de conectar y persisitir nuestras entidades
@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	 Optional<Usuario> findByNombre(String nombre);
	 
	 Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	 
	 boolean existsByNombre(String nombre);
	 
	 boolean existsByEmail(String email);

	 boolean existsById(Integer id);

	boolean existsByNombreUsuario(String nombreUsuario);
	 }
