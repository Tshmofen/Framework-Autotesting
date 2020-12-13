package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends CommonPage {

    public ProductPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public ProductPage openProductPage(String productPage) {
        log.info("Opening product page: " + productPage);
        driver.get(productPage);
        return this;
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
