package com.juancarlosmaya.stepdefinition.updateservice;

import com.juancarlosmaya.setup.ServiceSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.juancarlosmaya.util.ReadJsonFileCredentials;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateServiceStepDefinition extends ServiceSetUp {

    public static final Logger LOGGER= Logger.getLogger(com.juancarlosmaya.stepdefinition.loginservice.LoginServiceStepDefinition.class);
    private Response response;
    private RequestSpecification request;
    private ReadJsonFileCredentials data;

    @Given("Que el usuario se encuentra en la pagina de actualizacion de datos y desea cambiar su nombre a {string} y su trabajo a {string}")
    public void que_el_usuario_se_encuentra_en_la_pagina_de_actualizacion_de_datos_y_desea_cambiar_su_nombre_a_y_su_trabajo_a(String name, String job) {
        try {
            data = new ReadJsonFileCredentials(name,job,2);
            generalSetup();
            request = given()
                    .contentType(ContentType.JSON)
                    .body(data.getUsers().toJSONString());
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(),e);
            Assertions.fail(e.getMessage());
        }
    }

    @When("el usuario realiza la peticion de actualizacion")
    public void el_usuario_realiza_la_peticion_de_actualizacion() {
        try {
            response = request.when().patch(UPDATE_RESOURCE);
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("El usuario recibe un codigo de respuesta exitoso y una estructura con sus datos de usuario")
    public void el_usuario_recibe_un_codigo_de_respuesta_exitoso_y_una_estructura_con_sus_datos_de_usuario() {
        try {
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("name",notNullValue(),"job", notNullValue());
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
}
