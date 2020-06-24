package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.enums.RoleNombre;
import com.ecommerce.model.Role;

//interfaz que se encargara de conectar y persisitir nuestras entidades

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

	Optional<Role> findByRol(RoleNombre rol);

	


}
