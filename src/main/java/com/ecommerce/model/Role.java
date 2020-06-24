package com.ecommerce.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.ecommerce.enums.RoleNombre;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRol;

	@Enumerated(EnumType.STRING)
	private RoleNombre rol;

	public Role() {

	}

	public Role(RoleNombre rol) {

		this.rol = rol;

	}

	public Role(String rol) {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public RoleNombre getRol() {
		return rol;
	}

	public void setRol(RoleNombre rol) {
		this.rol = rol;
	}

	// me genera un codigo unico sobre el atributo id, que me permite diferenciarlo
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRol == null) ? 0 : idRol.hashCode());
		return result;
	}

	// me permite comparar objetos a traves del id
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (idRol == null) {
			if (other.idRol != null)
				return false;
		} else if (!idRol.equals(other.idRol))
			return false;
		return true;
	}

}
