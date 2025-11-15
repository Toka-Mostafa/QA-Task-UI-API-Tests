package api;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UsersApi extends ApiClient {

    public Response register(String name, String email, String password) {

        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("email", email);
        body.put("password", password);

        return given()
                .spec(requestSpec)                
                .contentType("application/json") 
                .body(body)
                .post("/users/register");
    }


    public Response login(String email, String password) {

        Map<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        return given()
                .spec(requestSpec)
                .contentType("application/json") 
                .body(body)
                .post("/users/login");
    }
    

    public Response changePassword(String token, String currentPassword, String newPassword) {

        Map<String, Object> body = new HashMap<>();
        body.put("currentPassword", currentPassword);
        body.put("newPassword", newPassword);

        return given()
                .spec(requestSpec)
                .header("x-auth-token", token)                
                .contentType("application/json") 
                .body(body)
                .post("/users/change-password");
    }

    
}
