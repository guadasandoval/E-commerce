package com.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.ecommerce.model.MetodoDePago;
import com.ecommerce.model.Usuario;
import com.ecommerce.model.Venta;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class MetodoDePagoServiceImpl  {

	@Value("${stripe.key.secret}")
	String claveSecreta;
	
	@Autowired
	VentaService ventaService;
	
	@Autowired
	UsuarioService usuarioService;

	   

	public PaymentIntent intentoPago(MetodoDePago metodoPago) throws StripeException {
		Stripe.apiKey = claveSecreta;

		// Map: Un objeto se usa como clave (índice) para otro objeto (valor

		Venta venta = metodoPago.getVenta();
		
		// Stripe requires the charge amount to be in cents
	    //int cargarMontoCtvs = (int) ventaService.getTotalVenta(venta) * 100;
	    int cargarMontoCtvs = 80*100;
	    Usuario usuarioCliente = venta.getUsuario();

		Map<String, Object> cargarDatos = new HashMap<String, Object>();
	
		cargarDatos.put("amount", cargarMontoCtvs);
		cargarDatos.put("currency", metodoPago.getMoneda());
		cargarDatos.put("description", metodoPago.getDescripcion());		
	//	cargarDatos.put("customer", usuarioCliente);
	//	cargarDatos.put("source", token);

		ArrayList tipoMetodoPago = new ArrayList();

		// se pueden anexar mas metodos de pago
		tipoMetodoPago.add("card");

		cargarDatos.put("payment_method_types", tipoMetodoPago);
		return PaymentIntent.create(cargarDatos);
	}

	public PaymentIntent confirmar(String id) throws StripeException {
		Stripe.apiKey = claveSecreta;

		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		Map<String, Object> pago = new HashMap<>();

		pago.put("metodoPago", "tarjeta_visa");

		paymentIntent.confirm(pago);

		return paymentIntent;
	}

	public PaymentIntent cancelar(String id) throws StripeException {
		Stripe.apiKey = claveSecreta;

		// Recupera los detalles de un PaymentIntent que se ha creado previamente.
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		paymentIntent.cancel();
		return paymentIntent;
	}
}
