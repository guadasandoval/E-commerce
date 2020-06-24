package com.ecommerce.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// muchas facturas a un cliente
	@ManyToOne
	private Usuario usuario;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
	private Set<DetalleVenta> detalleVenta = new HashSet<DetalleVenta>();

	private Date fecha;

	public Venta() {

	}

	public Venta(Integer id, Usuario usuario, Set<DetalleVenta> detalleVenta, Date fecha) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.detalleVenta = detalleVenta;
		this.fecha = fecha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setIdUser(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(Set<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
