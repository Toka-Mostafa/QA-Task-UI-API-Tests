package api;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    protected static Dotenv dotenv = Dotenv.load();
    protected RequestSpecification requestSpec;

    public ApiClient() {

        requestSpec = new RequestSpecBuilder()
        .setBaseUri(dotenv.get("NOTES_API_BASE_URL"))
        .setAccept("application/json")
        .build();
    }
}
