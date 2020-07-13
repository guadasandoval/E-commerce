package com.ecommerce.controllerTest;

import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import junit.framework.TestCase;

public class ProductoControllerTest extends TestCase {
	
	@Test
	public void listarProductos() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/v1/productos")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void altaProducto() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/v1/producto")
				.header("Content-Type", "application/json")
				
				.body("{\"nombre\":\"prodTest\"," + " \"categoria\":\"Tecnologia\"," + " \"codigo\":\"1\","
						+ " \"stock\":\"1000\"," + " \"precio\":\"950\"}")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(201, jsonResponse.getStatus());
	}

	
	@Test
	public void ProductoId() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/v1/producto/{id}")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
				.routeParam("id", "7")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void bajaProducto() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.delete("http://localhost:8080/v1/producto/{id}")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
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
