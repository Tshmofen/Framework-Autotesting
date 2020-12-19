package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {
    private static final String CATALOG_PAGE = "https://ram.by/catalogsearch";
    private static final String ITEM_PRICE_TEMPLATE = "//div[{item}]//div[@class='price']";

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

    public List<Double> getSearchPrices(int itemsToFind) {
        log.info("Getting first " + itemsToFind + " items prices");

        List<Double> prices = new ArrayList<>(itemsToFind);
        for(int i = 1; i <= itemsToFind; i++) {
            String itemPath = ITEM_PRICE_TEMPLATE.replace("{item}", String.valueOf(i));
            String itemPriceStr = searchItems.findElement(By.xpath(itemPath)).getText();
            double itemPrice = parsePriceToDouble(itemPriceStr);
            prices.add(itemPrice);
        }

        return prices;
    }

    public static double parsePriceToDouble(String priceStr) {
        LogProvider.getLog().info("Parsing string into double");
        return Double.parseDouble(
                priceStr
                .split(" ")[0]
                .replace(",", ".")
        );
    }
}
