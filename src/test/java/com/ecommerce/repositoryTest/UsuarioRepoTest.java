package com.ecommerce.repositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.ecommerce.ecommerce.EcommerceApplication;
import com.ecommerce.model.Usuario;
import com.ecommerce.repository.UsuarioRepo;

@SpringBootTest(classes = EcommerceApplication.class)
@Profile("test")
public class UsuarioRepoTest {

	@Autowired
	private UsuarioRepo usuarioRepo;

	

	@Test
	public void crearUsuarioTest() {
		Usuario usuarioTest = usuarioRepo.save(new Usuario("tina", "tinaPrueba", "tina@prueba.com", "1234"));
		Usuario usuarioEncontrado = usuarioRepo.findById(usuarioTest.getIdUsuario()).get();

		assertNotNull(usuarioEncontrado);
		assertEquals(usuarioTest.getIdUsuario(), usuarioEncontrado.getIdUsuario());
	}

	@Test
	public void buscarUsuarioPorNombre() {
		Usuario usuarioTest = usuarioRepo.save(new Usuario("pepe", "pepePrueba", "test@prueba.com", "1234"));
		Usuario usuarioEncontrado = usuarioRepo.findByNombre(usuarioTest.getNombre()).get();

		assertNotNull(usuarioEncontrado);
		assertEquals("pepe", usuarioEncontrado.getNombre());
	}

	@Test
	public void buscarUsuarioPorNombreUsuario() {
		Usuario usuarioTest = usuarioRepo.save(new Usuario("test", "testPrueba", "test@prueba.com", "1234"));
		Usuario usuarioEncontrado = usuarioRepo.findByNombreUsuario(usuarioTest.getNombreUsuario()).get();

		assertNotNull(usuarioEncontrado);
		assertEquals("testPrueba", usuarioEncontrado.getNombreUsuario());
	}

	

}
