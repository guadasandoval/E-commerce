package com.ecommerce.form;

public class DetalleForm {

	private int idProd;
	private int cantidadProd;
	
	public DetalleForm (int idProd, int cantidadProd) {
		this.idProd = idProd;
		this.cantidadProd= cantidadProd;
	}
	
	public int getIdProd() {
		return idProd;
	}
	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}
	public int getCantidadProd() {
		return cantidadProd;
	}
	public void setCantidadProd(int cantidadProd) {
		this.cantidadProd = cantidadProd;
	}
	
	
}
