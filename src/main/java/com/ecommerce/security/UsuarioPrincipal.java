package com.ecommerce.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.model.Usuario;

public class UsuarioPrincipal implements UserDetails {

	private Integer id;
	private String nombre;
	private String nombreUsuario;
	private String email;
	private String contrasena;
	private Collection<? extends GrantedAuthority> autoridad;
	
	
	public UsuarioPrincipal(Integer id, String nombre, String nombreUsuario, String email, String contrasena,
			Collection<? extends GrantedAuthority> autoridad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.contrasena = contrasena;
		this.autoridad = autoridad;
	}
	
	//el metodo build, crea una lista de privilegios (GrantedAutoriry) 
	//y se le asignan en el constructor al UsuarioPrincipal que devuelve el método.
	
	public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> autoridad =  usuario.getRol().stream().map(rol -> new SimpleGrantedAuthority(rol.getRol().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getIdUsuario(), usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getContrasena(), autoridad);
    }
	
	public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autoridad;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
