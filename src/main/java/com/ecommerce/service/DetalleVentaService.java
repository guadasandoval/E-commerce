package com.ecommerce.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.SinStockException;
import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Producto;
import com.ecommerce.repository.DetalleVentaRepo;
import com.ecommerce.repository.ProductoRepo;

@Service
@Transactional
public class DetalleVentaService {

	@Autowired
	DetalleVentaRepo detalleRepo;

	@Autowired
	ProductoRepo prodRepo;

	public Optional<DetalleVenta> obtenerPorId(Integer id) {
		return detalleRepo.findById(id);
	}
	
	public void guardar(DetalleVenta detalle) {
		detalleRepo.save(detalle);
	}

	public Float getTotalProducto(DetalleVenta detalle) {

		Producto prod = detalle.getProd();

		return (float) (prod.getPrecio() * detalle.getCantidad());
	}

	public void actualizarStock(DetalleVenta detalle) throws SinStockException {

		int nuevoStock;
		
	Producto prod = detalle.getProd(); 

		if (prod.getStock() >= detalle.getCantidad()) {
			nuevoStock = prod.getStock() - detalle.getCantidad();

			prod.setStock(nuevoStock);
		} else {
			throw new SinStockException("Sin stock");
		}
	}

	
}
