package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RamByVTBCalculatorPage {
    private static final String CALCULATOR_PAGE = "https://ram.by/komputery-v-kredit-noutbuki-v-kredit";
    private final WebDriver driver;
    private final Logger log;

    @FindBy(id = "cost_vtb")
    private WebElement priceInput;

    @FindBy(id = "contribution_rub_vtb")
    private WebElement initialFeeInput;

    @FindBy(id = "period_vtb")
    private WebElement selectTimeInput;

    @FindBy(xpath = "//*[@id='period_vtb']/option")
    private WebElement selectTimeOption;

    @FindBy(xpath = "//input[@onclick='calculate_vtb()']")
    private WebElement submitButton;

    @FindBy(id = "cost_in_credit_vtb")
    private WebElement finalPrice;

    public RamByVTBCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.log = LogProvider.getLog();
    }

    public RamByVTBCalculatorPage openPage() {
        log.info("Opening VTB calculator page");
        driver.get(CALCULATOR_PAGE);
        return this;
    }

    public RamByVTBCalculatorPage sendKeysToVTBForm(String productPrice, String initialFee) {
        log.info("Sending keys to VTB calculator");
        priceInput.sendKeys(productPrice);
        initialFeeInput.sendKeys(initialFee);
        selectTimeInput.click();
        selectTimeOption.click();
        return this;
    }

    public RamByVTBCalculatorPage submitVTBCalculatorForm() {
        log.info("Submitting VTB calculator form");
        submitButton.click();
        return this;
    }

    public String findFinalVTBCalculatorPrice() {
        log.info("Getting final price from VTB calculator form");
        return finalPrice.getText();
    }
}
