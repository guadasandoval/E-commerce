package com.ecommerce.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Usuario;
import com.ecommerce.model.Venta;

import com.ecommerce.repository.ProductoRepo;
import com.ecommerce.repository.VentaRepo;





@Service
@Transactional
public class VentaService {

	@Autowired
	VentaRepo ventaRepo;

	@Autowired
	DetalleVentaService detalleService;
	
	@Autowired
	ProductoRepo prodRepo;

	public List<Venta> listaVentas() {

		List<Venta> listado = ventaRepo.findAll();
		return listado;
	}

	public Optional<Venta> obtenerPorId(Integer id) {

		return ventaRepo.findById(id);

	}

	public void crearVenta(Venta venta) {

		ventaRepo.save(venta);
	}

	public void borrar(Integer id) {
		ventaRepo.deleteById(id);
		
	}

public double getTotalVenta(Venta venta) {
	
	double total = 0;
	
	for(DetalleVenta prodDetalle : venta.getDetalleVenta()) {
		
		total = total+ detalleService.getTotalProducto(prodDetalle);
		
	}
	
	return total;
}

public List<Venta> obtenerPorUsuario(Usuario user) {
	ventaRepo.findByUsuario(user);
	return null;
}

}
