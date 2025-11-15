package e2ePages;


import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    private static final Duration WAIT = Duration.ofSeconds(60,1);
    private WebElement element;

    public BasePage(WebDriver webDriver){this.driver = webDriver;}


    public void visitPageURL(String url) {
        driver.get(url);
    }


    protected void waitUntilElementIsPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    protected void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    protected void clickElement(By locator) {
        waitUntilElementIsPresent(locator);
        element= driver.findElement(locator);
        waitForElementToBeClickable(element);
        element.click();
    }


    protected void fillInElement(By locator, String value) {
        waitUntilElementIsPresent(locator);
        driver.findElement(locator).sendKeys(value);
    }


    protected void fillInElementByNumbers(By locator, int numericValue) {
        waitUntilElementIsPresent(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(String.valueOf(numericValue));
    }
    

    protected void waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    protected Boolean isElementDisplayed(By locator){
        waitForElementVisibility(locator);
        return driver.findElement(locator).isDisplayed();
    }


    public void refreshPage() {
        driver.navigate().refresh();
    }


    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }


    public boolean scrollUntilElementVisible(By locator, int maxScrolls, long pauseMs) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        for (int i = 0; i < maxScrolls; i++) {
            try {
                if (driver.findElement(locator).isDisplayed()) return true;
            } catch (Exception ignored) {}
            js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
            try { Thread.sleep(pauseMs); } catch (InterruptedException ignored) {}
        }
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    

    protected void scrollElementToBottom(By locator) {
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", 
        driver.findElement(locator));
    }


    public Dotenv dotenv = Dotenv.load();

    

}