package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends AbstractPage {
    private static final String ADD_PROGRESS_BAR_TEMPLATE = "//div[@id='progress' and @style='display: {state};']";
    private static final String PREVIOUS_LIST_TEMPLATE
            = "//div[@class='watched-list']//div[@class='content']//a[@href='{productPage}']";

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

    public ProductPage addProductToCart() {
        log.info("Adding product to cart");
        addToCartButton.click();
        // waiting until product adding window disappears (display: none;)
        String disappearedProgressPath = ADD_PROGRESS_BAR_TEMPLATE.replace("{state}", "none");
        waitForElementPresence(By.xpath(disappearedProgressPath));
        return this;
    }

    public CartingPage goToCartingPage() {
        log.info("Going to cart page");
        return new CartingPage(driver)
                .openCartPage();
    }

    public List<WebElement> getProductLinksInPreviousList(String productPage) {
        log.info("Getting all " + productPage + " links in previous products");
        String productsPath = PREVIOUS_LIST_TEMPLATE.replace("{productPage}", productPage);
        return waitForAllElementPresence(By.xpath(productsPath));
    }
}
