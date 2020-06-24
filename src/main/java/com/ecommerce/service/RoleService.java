package com.ecommerce.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.enums.RoleNombre;
import com.ecommerce.model.Role;
import com.ecommerce.repository.RoleRepo;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepo rolRepo;
	
	public Optional<Role> getByRolNombre (RoleNombre rolNombre){
		return rolRepo.findByRol(rolNombre);
	}
}
