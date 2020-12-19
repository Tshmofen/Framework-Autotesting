package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartingPage extends AbstractPage {
    private static final String CART_PAGE = "https://ram.by/cart";
    private static final String PRODUCT_LINK_TEMPLATE = "//a[@href='{href}']";

    public CartingPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public CartingPage openCartPage() {
        log.info("Opening cart page");
        driver.get(CART_PAGE);
        return this;
    }

    public List<WebElement> getAllProductLinks(String productPage) {
        log.info("Checking if product is in cart");
        String productLink = productPage.replace(SITE_URL, "");
        String productPath = PRODUCT_LINK_TEMPLATE.replace("{href}", productLink);
        waitForElementPresence(By.xpath(productPath));
        return driver.findElements(By.xpath(productPath));
    }
}
