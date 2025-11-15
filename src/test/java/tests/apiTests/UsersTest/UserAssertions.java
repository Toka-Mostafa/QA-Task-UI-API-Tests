package tests.apiTests.UsersTest;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class UserAssertions {

    public static void assertRegisterSuccess(Response response, String expectedEmail) {
        response.then()
                .statusCode(201)
                .body("success", equalTo(true))
                .body("status", equalTo(201))
                .body("message", equalTo("User account created successfully"))
                .body("data.email", equalTo(expectedEmail))
                .body("data.id", notNullValue());
    }


    public static void assertLoginSuccess(Response response, String expectedEmail) {
        response.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .body("message", equalTo("Login successful"))
                .body("data.email", equalTo(expectedEmail))
                .body("data.token", notNullValue())
                .body("data.id", notNullValue());
    }


    public static void assertLoginFail(Response response) {
        response.then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("status", equalTo(401))
                .body("message", containsString("Incorrect email address or password"));
    }
    

    public static void assertPasswordChangeSuccess(Response response) {
        response.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .body("message", equalTo("The password was successfully updated"));
    }

    
}
