package tests.e2eTests.AddProductsTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import e2ePages.HomePage;

public class HomePageAssertion {


    private HomePage homePage;

    public HomePageAssertion(WebDriver driver) {
        this.homePage = new HomePage(driver);
    }

    public void assertProductIsDisplayed(String productTitle) {
    Assert.assertTrue(homePage.isAddedProductDisplayedByTitle(productTitle));
}
    
}
