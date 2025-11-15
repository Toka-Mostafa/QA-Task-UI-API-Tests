package tests.e2eTests.SearchProductsTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import e2ePages.HomePage;

public class SearchProductsAssertions {

    
    private HomePage homePage;

    public SearchProductsAssertions(WebDriver driver) {
        this.homePage = new HomePage(driver);
    }

    

    public void assertMostResultsContainKeyword(String keyword, int expectedMinimumMatches) {
        boolean hasEnoughMatches = homePage.mostResultsContain(keyword, expectedMinimumMatches);
        
        Assert.assertTrue(
            hasEnoughMatches,
            String.format(
            "Expected at least %d search results to contain '%s' in title or description, but fewer were found.",
            expectedMinimumMatches, keyword
            )
        );
    }

    
}
