package com.epam.automation.ramby.page.calculator;

import com.epam.automation.ramby.model.CalculatorTransfer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorVTB extends CalculatorPage {
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

        CalculatorTransfer transfer = new CalculatorTransfer();
        transfer.priceInput= this.priceInput;
        transfer.initialFeeInput= this.initialFeeInput;
        transfer.selectTimeInput = this.selectTimeInput;
        transfer.selectTimeOption = this.selectTimeOption;
        transfer.submitButton= this.submitButton;
        transfer.finalPrice = this.finalPrice;
        transfer.feeWarning = null;
        super.initializeFields(transfer);
    }
}
