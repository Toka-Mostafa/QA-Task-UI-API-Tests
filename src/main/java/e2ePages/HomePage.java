package e2ePages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public HomePage(WebDriver webDriver){
        super(webDriver);
    }


    private String HOME_PAGE_PATH = dotenv.get("Freshnesecom_FRONTEND_URL");

    private By addProductIcon = By.cssSelector(".actions a[href='/add']");

    private By searchInput = By.cssSelector("input[placeholder='Search for products ...']");

    private By productCards = By.cssSelector(".grid.grid-cols-4 .flex.flex-col.p-4");

    private By productCardTitle = By.xpath(".//div[contains(@class,'cursor-pointer')][1]");

    private By productCardDescription = By.xpath(".//div[contains(@class,'text-xs')][1]");

    private By infiniteScroll = By.cssSelector(".infinite-scroll-component ");


    public String getHomePagePath() {
        return HOME_PAGE_PATH;
    }    


    public void clickAddProductIcon() {
        clickElement(addProductIcon);
    }


    public boolean isAddProductIconDisplayed() {
        return isElementDisplayed(addProductIcon);
    }


    private By productTitleElement(String productTitle) {
        return By.xpath("//div[contains(@class, 'hfQJgD') and normalize-space(text())='" + productTitle + "']");
    }


    public void searchFor(String keyword) {
        waitUntilElementIsPresent(searchInput);
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(keyword + Keys.ENTER);
    }


    private List<WebElement> getProductCards() {
        scrollUntilElementVisible(productCards,12,900);
        return driver.findElements(productCards);
    }


    private String getProductCardTitle(WebElement card) {
        return card.findElement(productCardTitle).getText();
    }


    private String getProductCardDescription(WebElement card) {
        return card.findElement(productCardDescription).getText();
    }

    
    public boolean isAddedProductDisplayedByTitle(String productTitle) {
        By productLocator = productTitleElement(productTitle);
        scrollUntilElementVisible(productLocator,20,600);
        return driver.findElement(productLocator).isDisplayed();
    }
    

    public List<String> getResultTexts() {
        return getProductCards().stream()
            .map(c -> (getProductCardTitle(c) + " " + getProductCardDescription(c)).toLowerCase().trim())
            .toList();
    }


    public void loadAllResults() {
        for (int i = 0; i < 10; i++) {
            int before = driver.findElements(productCards).size();
            scrollElementToBottom(infiniteScroll);
            try { Thread.sleep(400); } catch (InterruptedException ignored) {}
            if (driver.findElements(productCards).size() == before) break;
        }
    }


    public boolean mostResultsContain(String keyword, int minimumMatches) {
        loadAllResults();
        String searchKeyword = keyword.toLowerCase().trim();
        List<String> resultTexts = getResultTexts();
        if (resultTexts.isEmpty()) return false;

        long matchingResults = resultTexts.stream()
            .filter(text -> text.contains(searchKeyword))
            .count();

        return matchingResults >= minimumMatches;
    }



}
