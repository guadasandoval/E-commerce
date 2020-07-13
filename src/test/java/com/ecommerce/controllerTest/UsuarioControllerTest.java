package com.ecommerce.controllerTest;

import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import junit.framework.TestCase;

public class UsuarioControllerTest extends TestCase {

	@Test
	public void listarUsuarios() throws UnirestException {
		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/v1/lista")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void altaUsuario() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/v1/registrar")
				.header("Content-Type", "application/json")
				.body("{\"nombre\":\"test\"," + " \"email\":\"test@ada.com\"," + " \"nombreUsuario\":\"testPrueba\","
						+ " \"contrasena\":\"1234\"," + " \"roles\":[\"admin\"]}")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(201, jsonResponse.getStatus());
	}

	@Test
	public void login() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8080/v1/login")
				.header("Content-Type", "application/json")
				.body("{\"nombreUsuario\":\"testPrueba\"," + " \"contrasena\":\"1234\"}").asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void loginId() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/v1/login/{id}")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
				.routeParam("id", "7")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}
	
	@Test
	public void bajaUsuario() throws UnirestException {

		HttpResponse<JsonNode> jsonResponse = Unirest.delete("http://localhost:8080/v1/login/{id}")
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuRVQiLCJpYXQiOjE1OTM3MzEyNjksImV4cCI6MTU5Mzc2NzI2OX0.7CHJgdMB4kVC7djsn9TD5_89_EVwpNZvMXPqA7oWkXUUtJhT24hpeD4K2Uw27QkWicu6PjX9Vgkb6ACXONB1Vw")
				.routeParam("id", "8")
				.asJson();

		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}
}