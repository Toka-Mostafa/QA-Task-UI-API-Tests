package base;

import driverManager.ChromeDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ScreenshotUtility;
import java.net.MalformedURLException;
import java.io.File;         
import java.util.Objects; 


public class BaseTest {
    public static WebDriver driver = null;
    protected Dotenv dotenv = Dotenv.load();
    protected final String Freshnesecom_FRONTEND_URL = dotenv.get("Freshnesecom_FRONTEND_URL");

    ChromeDriverManager driverManager;


    @BeforeMethod
    public void startDriver() throws MalformedURLException {
        driverManager = new ChromeDriverManager();
        driver = driverManager.getDriver();
    }


   
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtility.captureScreenshot(driver, result.getName());
        }

        if (driverManager != null) {
            driverManager.quitDriver();
        }
    }


    public WebDriver getDriver() {
        return driver;
    }


    protected String getTestImagePath(String fileName) {
    try {
        return new File(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource("testdata/" + fileName)
                ).toURI()
        ).getAbsolutePath();
    } catch (Exception e) {
        throw new RuntimeException("Failed to load test image: " + fileName, e);
    }
}


}
