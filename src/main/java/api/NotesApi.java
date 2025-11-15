package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class NotesApi extends ApiClient {

   public Response createNote(String token, String title, String description, String category) {
    return given()
            .spec(requestSpec)
            .header("x-auth-token", token)
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParam("title", title)
            .formParam("description", description)
            .formParam("category", category)
            .post("/notes");
}


public Response updateNote(String token, String noteId, String title, String description, String category) {
    return given()
            .spec(requestSpec)
            .header("x-auth-token", token)
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParam("title", title)
            .formParam("description", description)
            .formParam("category", category)
            .formParam("completed", false) 
            .put("/notes/" + noteId);
}


    public Response deleteNote(String token, String noteId) {
        return given()
                .spec(requestSpec)
                .header("x-auth-token", token)
                .delete("/notes/" + noteId);
    }

    
}
