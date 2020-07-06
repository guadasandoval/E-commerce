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
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;

@Service
public class MetodoDePagoServiceImpl {

	@Value("${stripe.key.secret}")
	String claveSecreta;

	@Autowired
	VentaService ventaService;

	@Autowired
	UsuarioService usuarioService;

	public void crearCliente(MetodoDePago metodoPago, Customer customer) throws StripeException {

		Venta venta = metodoPago.getVenta();
		Usuario usuarioCliente = venta.getUsuario();

		if (customer.getEmail().equals(usuarioCliente.getEmail())) {
			Customer clienteExists = Customer.retrieve(customer.getId());
		} else {

			Map<String, Object> clienteParam = new HashMap<String, Object>();
			clienteParam.put("name", usuarioCliente.getNombre());
			clienteParam.put("email", usuarioCliente.getEmail());

			// Create customer
			Customer nuevoCliente = Customer.create(clienteParam);
		}

	}

	public PaymentIntent intentoPago(MetodoDePago metodoPago) throws StripeException {
		Stripe.apiKey = claveSecreta;

		// Map: Un objeto se usa como clave (índice) para otro objeto (valor

		Venta venta = metodoPago.getVenta();
//		Usuario usuarioCliente = venta.getUsuario();

		Customer clienteExists = Customer.retrieve("cus_HYuLFmO1cztkpC");

		Map<String, Object> cardParams = new HashMap<String, Object>();
		cardParams.put("number", "4242424242424242");
		cardParams.put("exp_month", "11");
		cardParams.put("exp_year", "2022");
		cardParams.put("cvc", "123");
		
		Map<String, Object> tokenParams = new HashMap<String, Object>();
		tokenParams.put("card", cardParams);
		
	Token token = Token.create(tokenParams);
		
		//objeto source acepta varios metodos de pago, instrumento de pago
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("source", token.getId());
		
		clienteExists.getSources().create(source);
		

		// int totalVenta = ventaService.getTotalVenta(venta) * 100;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", "8000");
		params.put("currency", metodoPago.getMoneda());
		params.put("description", metodoPago.getDescripcion());
		params.put("payment_method_types",cardParams);
		
		

		params.put("customer", clienteExists.getId());

		return PaymentIntent.create(params);

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
