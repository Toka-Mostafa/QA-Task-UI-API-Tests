package e2ePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddProductsPage extends BasePage{

    public AddProductsPage (WebDriver webDriver){super(webDriver);}

    private String ADD_PRODUCTS_PAGE_PATH = "/add";

    private By productImageUploadBox = By.cssSelector("input[type='file'][name='file']");

    private By titleField = By.name("title");

    private By descriptionField = By.cssSelector("input[name='description'][type='text']");

    private By descriptionLabel = By.xpath("//label[@for='description' and normalize-space()='Description']");

    private By priceField = By.name("price");

    private By createProductButton = By.cssSelector("button[type='submit']");

    private By priceValueValidationErrorMessage = By.xpath("//*[text()='Min price is 30 !']");

    private By titleLengthValidationErrorMessage = By.xpath("//*[text()='Min length is 10 !']");

    private By descriptionLengthValidationErrorMessage = By.xpath("//*[text()='Min length is 30 !']");

    private By emptyTetFieldRequiredValidationMessage = By.xpath("//*[text()='This field is required']");

    private By uploadedImagePreview = By.cssSelector(".image-preview");



    public String getProductsPagePath() {
        return ADD_PRODUCTS_PAGE_PATH;
    }


     public boolean isCreateProductButtonDisplayed() {
        return isElementDisplayed(createProductButton);
    }


    public void uploadProductImage(String absolutePathToProductImage) {
        driver.findElement(productImageUploadBox).sendKeys(absolutePathToProductImage);
    }


    public void fillInProductTitle(String productTitle) {
        fillInElement(titleField, productTitle);
    }


    public void fillInProductDescription(String productDescription) {
        fillInElement(descriptionField, productDescription);
    }

    public void fillInProductPrice(int productPrice) {
        fillInElementByNumbers(priceField, productPrice);
    }
    

    public void clickCreateProductButton() {
        clickElement(createProductButton);
    }
    

     public void pressDescriptionLabel() {
        clickElement(descriptionLabel);

    }


    public boolean isPriceValueValidationErrorMessageDisplayed() {
        return isElementDisplayed(priceValueValidationErrorMessage);
    }


    public boolean isTitleLengthValidationErrorMessageDisplayed() {
        return isElementDisplayed(titleLengthValidationErrorMessage);
    }


    public boolean isDescriptionLengthValidationErrorMessageDisplayed() {
        return isElementDisplayed(descriptionLengthValidationErrorMessage);
    }


    public boolean isEmptyFieldRequiredValidationMessageDisplayed() {
        return isElementDisplayed(emptyTetFieldRequiredValidationMessage);
    }

    public boolean isUploadedImagePreviewDisplayed() {
        return isElementDisplayed(uploadedImagePreview);
    }

    public int getRequiredFieldMessagesCount() {
        return driver.findElements(emptyTetFieldRequiredValidationMessage).size();
    }


    public void addProductSuccessfully(String absolutePathToProductImage,String productTitle,String productDescription, int productPrice  ) {
        uploadProductImage(absolutePathToProductImage);
        fillInProductDescription(productDescription);
        fillInProductTitle(productTitle);
        fillInProductPrice(productPrice);
        clickCreateProductButton();
    }


}
