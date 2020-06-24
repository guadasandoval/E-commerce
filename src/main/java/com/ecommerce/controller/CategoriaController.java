package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ecommerce.model.Categoria;
import com.ecommerce.repository.CategoriaRepo;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping
public class CategoriaController {

	@Autowired
	CategoriaRepo categoriaRepo;
	
	@PostMapping(path= "/addCategoria")
	@Operation (summary = "categoria", description = "crear categorias")
	public ResponseEntity<Categoria> creaCategoria (@RequestBody Categoria categoria){
		
		
		categoriaRepo.save(categoria);
		
		return new ResponseEntity<>(categoria, HttpStatus.CREATED);
		
	}
}
