package com.epam.automation.ramby.page.calculator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorVTB extends CalculatorsPage {
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

    public CalculatorVTB(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CalculatorVTB sendKeysToForm(String productPrice, String initialFee) {
        log.info("Sending keys to VTB calculator");
        priceInput.sendKeys(productPrice);
        initialFeeInput.sendKeys(initialFee);
        selectTimeInput.click();
        selectTimeOption.click();
        return this;
    }

    public CalculatorVTB submitCalculatorForm() {
        log.info("Submitting VTB calculator form");
        submitButton.click();
        return this;
    }

    public String findFinalCalculatorPrice() {
        log.info("Getting final price from VTB calculator form");
        return finalPrice.getText();
    }
}
