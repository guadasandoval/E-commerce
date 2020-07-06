package com.ecommerce.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Usuario;
import com.ecommerce.model.Venta;

@Repository
public interface VentaRepo extends JpaRepository<Venta, Integer>{

	public List<Venta> findByUsuario(Usuario user);
	
	

}
