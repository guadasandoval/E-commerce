package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Usuario;
import com.ecommerce.repository.UsuarioRepo;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepo usuarioRepo;
	
	public List<Usuario> obtenerTodos(){
	List<Usuario>lista = usuarioRepo.findAll();
	return lista;
	}
 
	public Optional<Usuario> obtenerPorId (Integer id){
		return usuarioRepo.findById(id);
	}

	public Optional<Usuario> obtenerPorNombre(String nombre){
		return usuarioRepo.findByNombre(nombre);
	}
	
	public Optional<Usuario> obtenerPorNombreUsuario(String nombreUsuario){
		return usuarioRepo.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existePorNombreUsuario(String nombreUsuario){
        return usuarioRepo.existsByNombreUsuario(nombreUsuario);
    }

	public boolean existePorNombre(String nombre){
        return usuarioRepo.existsByNombre(nombre);
    }
    public  boolean existePorEmail(String email){
        return usuarioRepo.existsByEmail(email);
    }
    
    public  boolean existePorId(Integer id){
        return usuarioRepo.existsById(id);
    }
	
	public void crearUsuario (Usuario user) {
		usuarioRepo.save(user);
	}
	
	public void borrarUsuario (Integer id) {
		usuarioRepo.deleteById(id);
	}
}
