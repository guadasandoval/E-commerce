package com.ecommerce.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
	
	@NotBlank
	private String nombreUsuario;
	@NotBlank
	private String contrasena;
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	

}
