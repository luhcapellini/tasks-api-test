package com.luhcapellini;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.Theories;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITeste {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8002/tasks-backend";
    }

    @Test
    public void deRetornarTarefas() {
        RestAssured.given()
        .when()
            .get("/todo")
        .then()
            .statusCode(200);
    }
    
    @Test
    public void deveAdicionarTarefas() {
        RestAssured.given()
            .body("{\"task\": \"Teste via API\", \"dueDate\": \"2021-12-30\"}")
            .contentType(ContentType.JSON)
        .when()
            .post("/todo")
        .then()
            .statusCode(201);
    }

    @Test
    public void naodeveAdicionarTarefaInvalida() {
        RestAssured.given()
            .body("{\"task\": \"Teste via API\", \"dueDate\": \"2020-12-30\"}")
            .contentType(ContentType.JSON)
        .when()
            .post("/todo")
        .then()
            .statusCode(400)
            .body("message", CoreMatchers.is("Due date must not be in past"));
    }
}


