package tests.e2eTests.AddProductsTest;

import e2ePages.AddProductsPage;
import e2ePages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import com.github.javafaker.Faker;


public class AddProductsTest extends BaseTest {

    HomePage homePage;
    AddProductsPage addProductsPage;
    AddProductsAssertion addProductsAssertion;
    HomePageAssertion homePageAssertion;
    Faker faker = new Faker();

    @BeforeMethod
    public void setUp() {
        initializedObjects();
        homePage.visitPageURL(dotenv.get("Freshnesecom_FRONTEND_URL"));
    }
    


    @Test(priority = 1, description = "Add a new product and verify it's listed on Home Test")
    public void testAddProductSuccessfully(){
        addProductsAssertion.assertAddProductIconVisibleOnHome();
        homePage.clickAddProductIcon();
        addProductsAssertion.assertOnAddProductPage();

        String title = faker.commerce().productName() +" New Product";
        String description = faker.lorem().sentence(8 , 10);
        int price = faker.number().numberBetween(31, 200); 
        String imagePath = getTestImagePath("dummy.jpg");

        addProductsPage.addProductSuccessfully(imagePath, title, description, price);

        addProductsAssertion.assertUploadedImagePreviewIsDisplayed();
        homePageAssertion.assertProductIsDisplayed(title);

    }



    @Test(priority = 2, description = "Price validation message is displayed when less than 30 Test")
    public void testPriceValidationMessageForLowPrice() {
        homePage.clickAddProductIcon();
        int lowPrice = 20;
        addProductsPage.fillInProductPrice(lowPrice);
        addProductsPage.pressDescriptionLabel();
        addProductsAssertion.assertPriceValidationErrorMessageDisplayed();
    }
    


    @Test(priority = 3, description = "Title & Description validation message is displayed when less than limit Test")
    public void testTitleAndDescriptionValidationMessageForShortValues() {
        homePage.clickAddProductIcon();
        String title = "Title ";
        String description = "description";
       
        addProductsPage.fillInProductDescription(description);
        addProductsPage.fillInProductTitle(title);
        addProductsPage.pressDescriptionLabel();
        addProductsAssertion.assertDescriptionLengthValidationMessageDisplayed();
        addProductsAssertion.assertTitleLengthValidationMessageDisplayed();
    }



    @Test(priority = 4, description = "Required field message when title/description are empty Test")
    public void testRequiredFieldValidationForEmptyTextFields() {
        homePage.clickAddProductIcon();
        addProductsPage.clickCreateProductButton();
        addProductsAssertion.assertMultipleRequiredFieldValidationMessagesDisplayed(2);
    }




    private void initializedObjects() {
        homePage = new HomePage(driver);
        addProductsPage = new AddProductsPage(driver);
        addProductsAssertion = new AddProductsAssertion(driver);
        homePageAssertion = new HomePageAssertion(driver);
    }
    
}
