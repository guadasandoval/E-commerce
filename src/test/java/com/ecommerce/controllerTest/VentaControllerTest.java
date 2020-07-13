package com.ecommerce.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import junit.framework.TestCase;

@SpringBootTest
public class VentaControllerTest extends TestCase {

	@Test
	public void listarVentas() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/v1/venta")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void crearVenta() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/v1/venta")
				.header("Content-Type", "application/json")
				
				.body("{\"idUsuario\":\"7\"," + " \"detalleForm\":[\"idProd\":\"5\", \"cantidadProd\":\"5\"]}")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	
	@Test
	public void ventaPorUsuario() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/v1/venta/{id}")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
				.routeParam("id", "7")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void bajaVenta() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.delete("http://localhost:8080/v1/venta/{id}")
				.header("Content-Type", "application/json")
				
				.routeParam("id", "8")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void listarPorCategoria() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/v1/search/{nombre}")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
				.routeParam("nombre", "Tecnologia")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}
}
