package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends CommonPage{
    private static final String CATALOG_PAGE = "https://ram.by/catalogsearch";

    @FindBy(xpath = "//input[@placeholder='Поиск по каталогу']")
    private WebElement searchInput;

    @FindBy(id = "search_button")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='items list-view']//div[@class='title']//a")
    private WebElement firstItemTitle;

    @FindBy(xpath = "//div[@class='switchers']//select")
    private WebElement sortSelector;

    @FindBy(xpath = "//div[@class='switchers']//select/option[text()='недорогие']")
    private WebElement lowPriceSortOption;

    @FindBy(xpath = "//div[@class='items list-view']")
    private WebElement searchItems;

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

    public SearchPage selectSortingByLowPrice() {
        log.info("Selecting sorting by low price");
        sortSelector.click();
        lowPriceSortOption.click();
        return this;
    }

    public boolean isItemsPriceAscending() {
        log.info("Checking if items price is ascending");
        String firstItemPriceStr = searchItems.findElement(By.xpath("//div[1]//div[@class='price']")).getText();
        String secondItemPriceStr = searchItems.findElement(By.xpath("//div[2]//div[@class='price']")).getText();

        double firstItemPrice  = Double.parseDouble(
                firstItemPriceStr
                        .split(" ")[0]
                        .replace(",", ".")
        );
        double secondItemPrice = Double.parseDouble(
                secondItemPriceStr
                        .split(" ")[0]
                        .replace(",", ".")
        );

        return firstItemPrice < secondItemPrice;
    }
}
