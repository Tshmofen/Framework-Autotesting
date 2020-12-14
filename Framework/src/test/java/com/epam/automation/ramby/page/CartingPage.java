package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
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

    public CartingPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public CartingPage openCartPage() {
        log.info("Opening cart page");
        driver.get(CART_PAGE);
        return this;
    }

    public boolean isProductInCart(String productPage) {
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
