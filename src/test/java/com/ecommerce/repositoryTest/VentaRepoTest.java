package com.ecommerce.repositoryTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.ecommerce.ecommerce.EcommerceApplication;
import com.ecommerce.model.Categoria;
import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Producto;
import com.ecommerce.model.Usuario;
import com.ecommerce.model.Venta;
import com.ecommerce.repository.CategoriaRepo;
import com.ecommerce.repository.DetalleVentaRepo;
import com.ecommerce.repository.ProductoRepo;
import com.ecommerce.repository.UsuarioRepo;
import com.ecommerce.repository.VentaRepo;

@SpringBootTest(classes = EcommerceApplication.class)
@Profile("test")
public class VentaRepoTest {

	@Autowired
	private VentaRepo ventaRepo;

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private DetalleVentaRepo detalleRepo;

	@Autowired
	private CategoriaRepo categoriaRepo;

	@Autowired
	private ProductoRepo prodRepo;

	@Test
	public void crearVentaTest() {

//		Usuario usuarioTest = usuarioRepo.save(new Usuario("test", "testPrueba", "test@prueba.com", "1234"));
//		Usuario usuarioEncontrado = usuarioRepo.findById(usuarioTest.getIdUsuario()).get();

		Categoria cat = categoriaRepo.save(new Categoria("Belleza"));
		assertNotNull(cat);
		assertEquals("Belleza", cat.getNombre());

//		Producto prod1 = prodRepo.save(new Producto("Detergente", cat, 1256, 1000, 75));
//		Producto prod2 = prodRepo.save(new Producto("Esponja", cat, 1578, 1000, 43));
//		Date now = new Date();

//		Venta v1 = ventaRepo.save(new Venta(usuarioEncontrado, listDetalle, now));

//		DetalleVenta d1 = detalleRepo.save(new DetalleVenta(prod1, v1, 7, 75));
//		DetalleVenta d2 = detalleRepo.save(new DetalleVenta(prod2, v1, 7, 75));
//
//		listDetalle.add(d1);
//		listDetalle.add(d2);
//		
//		assertNotNull(v1);
//		assertEquals("test", v1.getUsuario().getNombre());
	}

}
