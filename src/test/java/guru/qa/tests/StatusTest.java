package guru.qa.tests;

import guru.qa.models.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static guru.qa.specs.GetUserListSpec.*;
import static guru.qa.specs.LoginSpec.*;
import static guru.qa.specs.UserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("api")
@Owner("EphimSh")
@Feature("api test practice")
public class StatusTest {


    @Test
    @DisplayName("successful login")
    void successfulLogin() {
        LoginRequestModel authData = new LoginRequestModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel loginResponse = step("make a request", () ->
                given(loginRequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));
        step("check the request", () -> {
            assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
        });
    }

    @Test
    @DisplayName("get list of unknown objects")
    void getListUnknown() {
        GetListUnknownResponseModel getListResponse = step("make a request", () ->
                given(getUserListSpec)
                        .when()
                        .get("/unknown")
                        .then()
                        .spec(getUserListResponseSpec)
                        .extract().as(GetListUnknownResponseModel.class)
        );

        step("check request", () -> {
            assertEquals(getListResponse.getData().size(), 6);
        });
    }

    @Test
    @DisplayName("aqua sky is in unknown objects list")
    void getColorFromListUnknown() {
        GetListUnknownResponseModel getListResponse = step("make a request", () ->
                given(getUserListSpec)
                        .when()
                        .get("/unknown")
                        .then()
                        .spec(getUserListResponseSpec)
                        .extract().as(GetListUnknownResponseModel.class)
        );

        step("check request", () -> {
            assertEquals(getListResponse.getData().get(3).getName(), "aqua sky");
        });
    }

    @Test
    @DisplayName("successful user create")
    void postCreateUser() {
        UserRequestModel newUser = new UserRequestModel();
        newUser.setName("Vlad Tepes");
        newUser.setJob("The Impaler");

        UserResponseModel createUserResponse = step("make a request", () ->
                given(createUserSpec)
                        .body(newUser)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .extract().as(UserResponseModel.class)
        );

        step("check request", () -> {
            assertEquals("The Impaler", createUserResponse.getJob());
        });
    }

    @Test
    @DisplayName("successful user update")
    void patchCreateUser() {
        UserRequestModel newUser = new UserRequestModel();
        newUser.setName("Vlad Tepes");
        newUser.setJob("The Impaler");

        UserResponseModel createUserResponse = step("make a request", () ->
                given(createUserSpec)
                        .body(newUser)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(patchUserResponseSpec)
                        .extract().as(UserResponseModel.class)
        );

        step("check request", () -> {
            assertEquals("The Impaler", createUserResponse.getJob());
        });
    }

}
