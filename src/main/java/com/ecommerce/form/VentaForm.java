package com.ecommerce.form;

import java.util.List;

public class VentaForm {

	
	private int idUsuario;
	private List< DetalleForm> detalleForm;
	

	
	
	
	public List<DetalleForm> getDetalleForm() {
		return detalleForm;
	}
	public void setDetalleForm(List<DetalleForm> detalleForm) {
		this.detalleForm = detalleForm;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


}
