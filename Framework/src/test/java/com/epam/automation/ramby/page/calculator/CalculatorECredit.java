package com.epam.automation.ramby.page.calculator;

import com.epam.automation.ramby.model.CalculatorTransfer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorECredit extends CalculatorPage {
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

        CalculatorTransfer transfer = new CalculatorTransfer();
        transfer.priceInput= this.priceInput;
        transfer.initialFeeInput= this.initialFeeInput;
        transfer.selectTimeInput = this.selectTimeInput;
        transfer.selectTimeOption = this.selectTimeOption;
        transfer.submitButton= this.submitButton;
        transfer.finalPrice = this.finalPrice;
        transfer.feeWarning = this.feeWarning;
        super.initializeFields(transfer);
    }
}
