package listeners;

import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {

     private WebDriver getDriver(ITestResult result) {
        try {
            return ((base.BaseTest) result.getInstance()).getDriver();
        } catch (Exception e) {
            return null;
        }
    }


    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver(result);
        if (driver != null) {
            saveScreenshot(driver);
        }
    }


    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onTestSkipped(ITestResult result) {}


}
