package com.ecommerce.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// un producto puede estar en muchos detalles de venta
	@ManyToOne
	@JoinColumn
	private Producto prod;

	@JsonBackReference
	@ManyToOne
	private Venta venta;

	private int cantidad;

	private int precio;

	public DetalleVenta(Integer id, Producto prod, Venta venta, int cantidad, int precio) {

		this.id = id;
		this.prod = prod;
		this.venta = venta;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public DetalleVenta() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Producto getProd() {
		return prod;
	}

	public void setProd(Producto prod) {
		this.prod = prod;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}
