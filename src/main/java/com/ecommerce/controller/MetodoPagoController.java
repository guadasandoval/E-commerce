package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.enums.Currency;
import com.ecommerce.form.PagoForm;
import com.ecommerce.model.MetodoDePago;
import com.ecommerce.model.Venta;
import com.ecommerce.service.MetodoDePagoServiceImpl;
import com.ecommerce.service.VentaService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping(path = "/v1")
public class MetodoPagoController {

	@Autowired
	MetodoDePagoServiceImpl pagoService;

	@Autowired
	VentaService ventaService;

	// devolvemos un string con el PaymentIntent convertido en JSON.
	
	@PostMapping("/pagar")
	public ResponseEntity<MetodoDePago> crearPago(@RequestBody PagoForm pagoForm) throws StripeException {

	
		MetodoDePago metodoPago = new MetodoDePago();
		Venta venta = ventaService.obtenerPorId(pagoForm.getIdVenta()).get();

		
		metodoPago.setMoneda(Currency.valueOf(pagoForm.getMoneda()));
		metodoPago.setVenta(venta);
		metodoPago.setDescripcion(pagoForm.getDescripcion());
	
	

		PaymentIntent intentoPago = pagoService.intentoPago(metodoPago);
		return new ResponseEntity(intentoPago, HttpStatus.OK);
	}

	
	@PostMapping("/confirmar/{id}")
	public ResponseEntity<String> confirmarPago(@PathVariable("id") String id) throws StripeException {
		PaymentIntent paymentIntent = pagoService.confirmar(id);
		String paymentStr = paymentIntent.toJson();
		return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
	}

	@PostMapping("/cancelar/{id}")
	public ResponseEntity<String> cancelarPago(@PathVariable("id") String id) throws StripeException {
		PaymentIntent paymentIntent = pagoService.cancelar(id);
		String paymentStr = paymentIntent.toJson();
		return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
	}
}
