package tests.apiTests.UsersTest;

import api.UsersApi;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserE2ETests {

    private UsersApi usersApi;
    private Faker faker;
    private String name;
    private String email;
    private String initialPassword;
    private String newPassword;
    private String token;

    
    @BeforeClass
    public void setup() {
        initializedObjects();
        
        // Generate test data
        name = faker.name().fullName();
        email = faker.internet().emailAddress();
        initialPassword = "Init-" + faker.internet().password(8, 12) + "!1";
        newPassword = "New-" + faker.internet().password(8, 12) + "!2";
    }


    @Attachment(value = "Response Body", type = "application/json")
    public String attach(Response response) {
        return response.asPrettyString();
    }


    @Test(priority = 1, description = "Register a new user")
    @Description("Creates a new user using")
    public void test_registerUser() {

        Response response = usersApi.register(name, email, initialPassword);
        attach(response);

        UserAssertions.assertRegisterSuccess(response, email);
    }


    @Test(priority = 2, description = "Login with the initial password")
    @Description("Login with the initial password")
    public void test_loginWithOldPassword() {

        Response response = usersApi.login(email, initialPassword);
        attach(response);

        UserAssertions.assertLoginSuccess(response, email);
        token = response.path("data.token");
    }


    @Test(priority = 3, description = "Change password successfully")
    @Description("Change password successfully")
    public void test_changePassword() {

        Response response = usersApi.changePassword(token, initialPassword, newPassword);
        attach(response);

        UserAssertions.assertPasswordChangeSuccess(response);
    }


    @Test(priority = 4, description = "Login with the NEW password")
    @Description("Verifies user can login after password change")
    public void test_loginWithNewPassword() {

        Response response = usersApi.login(email, newPassword);
        attach(response);

        UserAssertions.assertLoginSuccess(response, email);
    }


    @Test(priority = 5, description = "Login with OLD password should now fail")
    @Description("Verifies old password no longer works after update")
    public void test_loginWithOldPassword_shouldFail() {

        Response response = usersApi.login(email, initialPassword);
        attach(response);

        UserAssertions.assertLoginFail(response);
    }


    private void initializedObjects() {
        usersApi = new UsersApi();
        faker = new Faker();
    }

    
}
