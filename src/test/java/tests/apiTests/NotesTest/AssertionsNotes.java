package tests.apiTests.NotesTest;


import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class AssertionsNotes {

    public static void assertCreateNoteSuccess(Response response, String title, String description, String category) {

        response.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Note successfully created"))
                .body("data.id", notNullValue())
                .body("data.title", equalTo(title))
                .body("data.description", equalTo(description))
                .body("data.category", equalTo(category))
                .body("data.completed", equalTo(false));
    }


    public static void assertUpdateNoteSuccess(Response response, String noteId, String updatedTitle, String updatedDescription, String category) {

        response.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Note successfully Updated"))
                .body("data.id", equalTo(noteId))
                .body("data.title", equalTo(updatedTitle))
                .body("data.description", equalTo(updatedDescription))
                .body("data.category", equalTo(category));
    }


    public static void assertDeleteNoteSuccess(Response response) {

        response.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("message", equalTo("Note successfully deleted"));
    }


}
