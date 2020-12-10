package com.epam.automation.ramby.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RamByVTBCalculatorPage {
    private static final String CALCULATOR_PAGE = "https://ram.by/komputery-v-kredit-noutbuki-v-kredit";
    private final WebDriver driver;

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
    }

    public RamByVTBCalculatorPage openPage() {
        driver.get(CALCULATOR_PAGE);
        return this;
    }

    public RamByVTBCalculatorPage sendKeysToVTBForm(String productPrice, String initialFee) {
        priceInput.sendKeys(productPrice);
        initialFeeInput.sendKeys(initialFee);
        selectTimeInput.click();
        selectTimeOption.click();

        return this;
    }

    public RamByVTBCalculatorPage submitVTBCalculatorForm() {
        submitButton.click();
        return this;
    }

    public String findFinalVTBCalculatorPrice() {
        return finalPrice.getText();
    }
}
