package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage extends CommonPage{
    private static final String CATALOG_PAGE = "https://ram.by/catalogsearch";

    @FindBy(xpath = "//input[@placeholder='Поиск по каталогу']")
    private WebElement searchInput;

    @FindBy(id = "search_button")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='items list-view']//div[@class='title']//a")
    private WebElement firstItemTitle;

    public SearchPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
        PageFactory.initElements(driver, this);
    }

    public SearchPage openPage() {
        log.info("Opening catalog page");
        driver.get(CATALOG_PAGE);
        return this;
    }

    public SearchPage sendKeyToSearchForm(String key) {
        log.info("Sending key to search form");
        searchInput.sendKeys(key);
        return this;
    }

    public SearchPage submitSearchButton() {
        log.info("Submitting search button");
        searchButton.click();
        return this;
    }

    public String getFirstItemTitle() {
        log.info("Taking first item title");
        return firstItemTitle.getText();
    }
}
