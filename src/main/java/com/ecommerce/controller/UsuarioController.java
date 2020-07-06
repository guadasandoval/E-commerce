package com.ecommerce.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Role;
import com.ecommerce.dto.JwtDTO;
import com.ecommerce.dto.LoginUsuario;
import com.ecommerce.dto.NuevoUsuario;
import com.ecommerce.enums.RoleNombre;
import com.ecommerce.model.Usuario;
import com.ecommerce.security.JWT.JwtProvider;
import com.ecommerce.service.RoleService;
import com.ecommerce.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

// Redirecciona la peticion de un usuario
// hacia una logica de negocio service
// Exponemos los metodos

@RestController
@RequestMapping(path = "/v1")
public class UsuarioController {

	Log log = LogFactory.getLog(UsuarioController.class);

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RoleService rolService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@GetMapping(path = "/lista")
	@Operation(summary = "Lista de usuarios", description = "Obtener lista de usuarios registrados")
	public ResponseEntity<List<Usuario>> listar() {

		List<Usuario> lista = usuarioService.obtenerTodos();

		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@PostMapping(path = "/registrar")
	@Operation(summary = "Crear", description = "registro de usuarios")
	public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody NuevoUsuario usuario, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
            return new ResponseEntity("Campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
		
		if (usuario.getEmail().isEmpty()) {
			return new ResponseEntity("Campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
		}

		if (usuarioService.existePorNombreUsuario(usuario.getNombreUsuario())) {
			return new ResponseEntity("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		if (usuarioService.existePorEmail(usuario.getEmail())) {
			return new ResponseEntity("Ese email ya existe", HttpStatus.BAD_REQUEST);

		}

		Usuario nuevoUsuario = new Usuario(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(),
				passwordEncoder.encode(usuario.getContrasena()));

		Set<String> rolesStr = usuario.getRoles();
		
		Set<Role> roles = new HashSet<>();
		
		for (String rol : rolesStr) {
			
			switch (rol) {
			case "admin":
				Role rolAdmin = rolService.getByRolNombre(RoleNombre.ROLE_ADMIN).get();
				roles.add(rolAdmin);
				break;
			default:
				Role rolUser = rolService.getByRolNombre(RoleNombre.ROLE_USER).get();
				roles.add(rolUser);
			}
		}
		
		nuevoUsuario.setRol(roles);
		

		usuarioService.crearUsuario(nuevoUsuario);
		return new ResponseEntity<Usuario>(nuevoUsuario, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getContrasena())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }

	@GetMapping(path = "/login/{id}")
	@Operation(summary = "Usuario id", description = "Obtener usuarios por id")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
		if (usuarioService.existePorId(id)) {

			Usuario user = usuarioService.obtenerPorId(id).get();

			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity("El id ingresado no existe", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(path = "/login/{id}")
	public ResponseEntity<Usuario> borrarUsuario(@PathVariable Integer id) {
		if (usuarioService.existePorId(id)) {

			usuarioService.borrarUsuario(id);
			return ResponseEntity.noContent().build();

		}
		return new ResponseEntity("El id ingresado no existe", HttpStatus.BAD_REQUEST);
	}

}
