package api;

import io.github.cdimascio.dotenv.Dotenv;

public class ApiConfig {

    protected static final Dotenv dotenv = Dotenv.load();

    public static final String BASE_URL = dotenv.get("NOTES_API_BASE_URL");

    
}
