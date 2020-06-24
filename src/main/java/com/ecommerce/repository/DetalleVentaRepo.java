package com.ecommerce.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.DetalleVenta;

@Repository
public interface DetalleVentaRepo extends JpaRepository<DetalleVenta, Integer> {

	public Optional<DetalleVenta> findById (Integer id);
}
