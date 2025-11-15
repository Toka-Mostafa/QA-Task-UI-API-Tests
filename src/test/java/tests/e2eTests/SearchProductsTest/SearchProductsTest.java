package tests.e2eTests.SearchProductsTest;

import base.BaseTest;
import e2ePages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchProductsTest extends BaseTest{


    HomePage homePage;
    SearchProductsAssertions searchProductsAssertions;

    @BeforeMethod
    public void setUp() {
        initializeObjects();
        homePage.visitPageURL(dotenv.get("Freshnesecom_FRONTEND_URL"));
    }


    @Test(priority = 1, description = "Search for a keyword and verify results match in title or description Test")
    public void testSearchResultsContainKeyword(){
        String keyword = "iphone";
        homePage.searchFor(keyword);
        homePage.loadAllResults();
        searchProductsAssertions.assertMostResultsContainKeyword(keyword, 3);
    }


    private void initializeObjects() {
        homePage = new HomePage(driver);
        searchProductsAssertions = new SearchProductsAssertions(driver);
    }

}

    

