package tests.apiTests.NotesTest;

import api.NotesApi;
import api.UsersApi;
import io.restassured.response.Response;
import com.github.javafaker.Faker;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class NotesE2ETests {

    private NotesApi notesApi;
    private UsersApi usersApi;
    private Faker faker;
    private String token;
    private String noteId;
    private String title;
    private String description;
    private String category;


    @Attachment(value = "Response Body", type = "application/json")
    public String attach(Response response) {
        return response.asPrettyString();
    }


    @BeforeClass
    public void setup() {
       
        initializedObjects();
        generateUserDataAndLoginThenSaveToken();

        // Generate test data for notes
        title = faker.book().title();
        description = faker.lorem().sentence();
        category = "Home";
    }



    @Test(priority = 1, description = "Create a new note")
    @Description("Create a new note")
    public void test_createNote() {

        Response response = notesApi.createNote(token, title, description, category);
        attach(response);

        AssertionsNotes.assertCreateNoteSuccess(response, title, description, category);

        noteId = response.path("data.id");
    }


    @Test(priority = 2, description = "Update an existing note")
    @Description("Update an existing note")
    public void test_updateNote() {

        String updatedTitle = title + " UPDATED";
        String updatedDescription = description + " UPDATED";

        Response response = notesApi.updateNote(token, noteId, updatedTitle, updatedDescription, category);
        attach(response);

        AssertionsNotes.assertUpdateNoteSuccess(response, noteId, updatedTitle, updatedDescription, category);
    }


    @Test(priority = 3, description = "Delete an existing note")
    @Description("Delete an existing note")
    public void test_deleteNote() {

        Response response = notesApi.deleteNote(token, noteId);
        attach(response);

        AssertionsNotes.assertDeleteNoteSuccess(response);
    }



    private void initializedObjects() {
        notesApi = new NotesApi();
        usersApi = new UsersApi();
        faker = new Faker();
    }
    

    private void generateUserDataAndLoginThenSaveToken(){

        // Generate new user Data
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = "Pass-" + faker.internet().password(8, 12) + "1!";

        // Register user
        Response registerResponse = usersApi.register(name, email, password);
        attach(registerResponse);

        registerResponse.then()
                .statusCode(201)
                .body("success", equalTo(true));

        // Login user
        Response loginResponse = usersApi.login(email, password);
        attach(loginResponse);

        loginResponse.then()
                .statusCode(200)
                .body("success", equalTo(true));

        token = loginResponse.path("data.token");

    }

}
