package com.juancarlosmaya.stepdefinition.loginservice;

import com.juancarlosmaya.setup.ServiceSetUp;
import com.juancarlosmaya.util.ReadJsonFileCredentials;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Assertions;



public class LoginServiceStepDefinition extends ServiceSetUp {
    public static final Logger LOGGER= Logger.getLogger(LoginServiceStepDefinition.class);
    private Response response;
    private RequestSpecification request;
    private ReadJsonFileCredentials data;

    //login exitoso
    @Given("el usuario esta en la pagina de inicio de sesion con el correo de ususario {string} y la contrasena {string}")
    public void el_usuario_esta_en_la_pagina_de_inicio_de_sesion_con_el_correo_de_ususario_y_la_contrasena(String user, String password) {
        try {
            data = new ReadJsonFileCredentials(user, password,1);
            generalSetup();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(data.getUsers().toJSONString());
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @When("cuando el usuario hace una peticion de inicio")
    public void cuando_el_usuario_hace_una_peticion_de_inicio() {
        try {
            response = request.when().post(REGISTER_RESOURCE);
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("el usuario debera ver un codigo de respuesta exitoso y un token de respuesta")
    public void el_usuario_debera_ver_un_codigo_de_respuesta_exitoso_y_un_token_de_respuesta() {
        try {
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("token", notNullValue());
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    //login: Olvido el password
    @Given("el usuario esta en la pagina de inicio de sesion con el correo de usuario {string} sin contrasena")
    public void el_usuario_esta_en_la_pagina_de_inicio_de_sesion_con_el_correo_de_usuario_sin_contrasena(String user) {
        try {
            data = new ReadJsonFileCredentials(user, "",1);
            generalSetup();
            request = given()
                    .log()
                    .all()
                    .contentType(ContentType.JSON)
                    .body(data.getUsers().toJSONString());
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }
    @Then("el usuario debera ver un codigo de error indicando que falta el password")
    public void el_usuario_debera_ver_un_codigo_de_error_indicando_que_falta_el_password() {
        try {
            response.then()
                    .log()
                    .all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("error", notNullValue());
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }




}
