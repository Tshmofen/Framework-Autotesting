package com.epam.automation.ramby.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RamByCartingPage {
    private static final String CART_PAGE = "https://ram.by/cart";
    private static final String SITE_URL = "https://ram.by/";
    private final WebDriver driver;

    @FindBy(xpath = "//a[@class='btn btn-2-cart btn-2c']")
    private WebElement addToCartButton;

    private final String productPage;

    public RamByCartingPage(WebDriver driver, String productPage) {
        this.driver = driver;
        this.productPage = productPage;
        PageFactory.initElements(driver, this);
    }

    public RamByCartingPage openProductPage() {
        driver.get(productPage);
        return this;
    }

    public RamByCartingPage addProductToCart() {
        addToCartButton.click();
        new WebDriverWait(driver, 10)
                .until(
                        ExpectedConditions.presenceOfElementLocated(
                                // waiting until product adding window disappears (display: none;)
                                By.xpath("//div[@id='progress' and @style='display: none;']")
                        )
                );
        return this;
    }

    public RamByCartingPage openCartPage() {
        driver.get(CART_PAGE);
        return this;
    }

    public boolean isProductInCart() {
        new WebDriverWait(driver, 5)
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
