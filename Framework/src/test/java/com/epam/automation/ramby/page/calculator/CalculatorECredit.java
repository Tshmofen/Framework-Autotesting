package com.epam.automation.ramby.page.calculator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorECredit extends CalculatorsPage {
    @FindBy(id = "cost_ecredit")
    private WebElement priceInput;

    @FindBy(id = "contribution_rub_ecredit")
    private WebElement initialFeeInput;

    @FindBy(id = "period_ecredit")
    private WebElement selectTimeInput;

    @FindBy(xpath = "//*[@id='period_ecredit']/option")
    private WebElement selectTimeOption;

    @FindBy(xpath = "//input[@onclick='calculate_ecredit()']")
    private WebElement submitButton;

    @FindBy(id = "cost_in_credit_ecredit")
    private WebElement finalPrice;

    @FindBy(xpath = "//span[@id='contribution_info_ecredit' and @style='color: red; font-weight: bold;']")
    private WebElement feeWarning;

    public CalculatorECredit(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CalculatorECredit openPage() {
        log.info("Opening ECredit calculator page");
        driver.get(CALCULATOR_PAGE);
        return this;
    }

    public CalculatorECredit sendKeysToForm(String productPrice, String initialFee) {
        log.info("Sending keys to e-credit calculator");
        priceInput.sendKeys(productPrice);
        initialFeeInput.sendKeys(initialFee);
        selectTimeInput.click();
        selectTimeOption.click();
        return this;
    }

    public CalculatorECredit submitCalculatorForm() {
        log.info("Submitting calculator e-credit form");
        submitButton.click();
        return this;
    }

    public boolean getPresenceOfFeeWarning() {
        return feeWarning.isEnabled();
    }

    public String findFinalCalculatorPrice() {
        log.info("Getting final price from e-credit calculator form");
        return finalPrice.getText();
    }
}
