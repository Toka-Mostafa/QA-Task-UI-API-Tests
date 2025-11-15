package tests.e2eTests.AddProductsTest;
import e2ePages.AddProductsPage;
import e2ePages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;



public class AddProductsAssertion {
    private AddProductsPage addProductsPage;
    private HomePage homePage;

    public AddProductsAssertion(WebDriver driver) {
        this.addProductsPage = new AddProductsPage(driver);
        this.homePage = new HomePage(driver);
    }


    public void assertOnAddProductPage() {
        Assert.assertTrue(addProductsPage.isCreateProductButtonDisplayed());
    }


    public void assertPriceValidationErrorMessageDisplayed() {
        Assert.assertTrue(addProductsPage.isPriceValueValidationErrorMessageDisplayed());
    }


    public void assertEmptyTextFieldRequiredValidationMessageDisplayed() {
        Assert.assertTrue(addProductsPage.isEmptyFieldRequiredValidationMessageDisplayed());
    }
    

    public void assertTitleLengthValidationMessageDisplayed() {
        Assert.assertTrue(addProductsPage.isTitleLengthValidationErrorMessageDisplayed());
    }


    public void assertDescriptionLengthValidationMessageDisplayed() {
        Assert.assertTrue(addProductsPage.isDescriptionLengthValidationErrorMessageDisplayed());
    }


    public void assertMultipleRequiredFieldValidationMessagesDisplayed(int expectedCountOfRequiredMessages) {
        int actualCount = addProductsPage.getRequiredFieldMessagesCount();
        Assert.assertEquals(actualCount, expectedCountOfRequiredMessages);
    }


    public void assertAddProductIconVisibleOnHome() {
        Assert.assertTrue(homePage.isAddProductIconDisplayed());
    }


    public void assertUploadedImagePreviewIsDisplayed() {
        Assert.assertTrue(addProductsPage.isUploadedImagePreviewDisplayed());
    }
    
}
