package com.ecommerce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

	public int getTotalVenta(Venta venta) {

		int total = 0;

		for (DetalleVenta prodDetalle : venta.getDetalleVenta()) {

			total += detalleService.getTotalProducto(prodDetalle);

		}

		return total;
	}

	public List<Venta> obtenerPorUsuario(Usuario user) {
		List<Venta> ventasUsuario = ventaRepo.findByUsuario(user);
		
		return ventasUsuario;
	}

}
