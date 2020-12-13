package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartingPage extends CommonPage{
    private static final String CART_PAGE = "https://ram.by/cart";

    private String productPage;

    @FindBy(xpath = "//a[@class='btn btn-2-cart btn-2c']")
    private WebElement addToCartButton;

    public CartingPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public CartingPage openProductPage(String productPage) {
        log.info("Opening product page");
        driver.get(productPage);
        this.productPage = productPage;
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
        return this;
    }

    public CartingPage openCartPage() {
        log.info("Opening cart page");
        driver.get(CART_PAGE);
        return this;
    }

    public boolean isProductInCart() {
        log.info("Checking if product is in cart");
        new WebDriverWait(driver, TIMEOUT)
                .until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//div[@class='item']")
                        )
                );
        String productLink = productPage.replace(SITE_URL, "");
        List<WebElement> products = driver.findElements(By.xpath("//a[@href='" + productLink + "']"));
        return products.size() == 1;
    }
}
