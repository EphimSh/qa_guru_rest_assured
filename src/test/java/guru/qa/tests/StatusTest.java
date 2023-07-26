package guru.qa.tests;

import guru.qa.models.LoginBodyLombokModel;
import guru.qa.models.LoginBodyPojoModel;
import guru.qa.models.LoginResponseLombokModel;
import guru.qa.models.LoginResponsePojoModel;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest extends TestBase{

    @Test
    void deleteUser(){
        given()
                .log().uri()
                .log().body()
                .log().method()
                .when()
                .delete("/users/3")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void patchUser(){
        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .contentType(ContentType.JSON)
                .body(data)
                .patch("/users/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("zion resident"));
    }

    @Test
    void successfulLogin(){

        LoginBodyPojoModel authData = new LoginBodyPojoModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponsePojoModel loginResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(ContentType.JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
    }

    @Test
    void successfulLogin1(){

        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(ContentType.JSON)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
    }

    @Test
    void successfulRegister(){
        String data = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void getListUsersSize(){
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasSize(6));
    }
}
