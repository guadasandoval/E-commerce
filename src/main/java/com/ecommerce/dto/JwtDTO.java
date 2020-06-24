package com.ecommerce.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDTO {

	private String token;
	private String bearer = "Bearer";
	private String nombreUsuario;
	private Collection<? extends GrantedAuthority> autoridad;
	
	
	public JwtDTO(String token, String nombreUsuario, Collection<? extends GrantedAuthority> autoridad) {
		super();
		this.token = token;
		this.nombreUsuario = nombreUsuario;
		this.autoridad = autoridad;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getBearer() {
		return bearer;
	}


	public void setBearer(String bearer) {
		this.bearer = bearer;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public Collection<? extends GrantedAuthority> getAutoridad() {
		return autoridad;
	}


	public void setAutoridad(Collection<? extends GrantedAuthority> autoridad) {
		this.autoridad = autoridad;
	} 
	
	
}
