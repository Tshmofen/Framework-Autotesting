package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends CommonPage {

    @FindBy(xpath = "//a[@class='btn btn-2-cart btn-2c']")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public ProductPage openProductPage(String productPage) {
        log.info("Opening product page: " + productPage);
        driver.get(productPage);
        return this;
    }

    public CartingPage addProductToCart() {
        log.info("Adding product to cart");
        addToCartButton.click();
        new WebDriverWait(driver, TIMEOUT)
                .until(
                        ExpectedConditions.presenceOfElementLocated(
                                // waiting until product adding window disappears (display: none;)
                                By.xpath("//div[@id='progress' and @style='display: none;']")
                        )
                );
        return new CartingPage(driver);
    }

    public boolean isProductInPreviousProducts(String productPage) {
        log.info("Check if " + productPage + " is in previous products");
        String productsXpath = "//div[@class='watched-list']//a[@href='" + productPage + "']";
        int linksNumber = new WebDriverWait(driver, TIMEOUT)
                .until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath(productsXpath)
                        )
                ).size();
        return linksNumber != 0;
    }
}
