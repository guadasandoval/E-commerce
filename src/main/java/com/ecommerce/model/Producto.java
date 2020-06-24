package com.ecommerce.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;



import com.ecommerce.model.Categoria;





@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String nombre;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Categoria categoria;
	
	private int codigo;
	private int stock;
	private int precio;
	
	
	public Producto() {
		
	}
	public Producto( String nombre, Categoria categoria, int codigo, int stock, int precio) {
		
		this.nombre = nombre;
		this.categoria = categoria;
		this.codigo = codigo;
		this.stock = stock;
		this.precio = precio;
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
		public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
	
	
	
}
