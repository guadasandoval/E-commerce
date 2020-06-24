package com.ecommerce.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ecommerce.enums.Currency;

@Entity
public class MetodoDePago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// una venta puede tener muchos metodos de pago
	@ManyToOne
	private Venta venta;
	private String descripcion;
	private double monto;
	private Currency moneda;
	

	public MetodoDePago(Venta venta, String descripcion, double monto, Currency moneda) {

		this.venta = venta;
	//	this.descripcion = descripcion;
		this.monto = monto;
		this.moneda = moneda;
	}

	public MetodoDePago() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Currency getMoneda() {
		return moneda;
	}

	public void setMoneda(Currency moneda) {
		this.moneda = moneda;
	}

}
