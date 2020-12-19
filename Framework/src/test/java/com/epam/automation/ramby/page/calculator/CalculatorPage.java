package com.epam.automation.ramby.page.calculator;

import com.epam.automation.ramby.model.CalculatorTransfer;
import com.epam.automation.ramby.page.AbstractPage;
import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class CalculatorPage extends AbstractPage {
    private static final String CALCULATOR_PAGE = "https://ram.by/komputery-v-kredit-noutbuki-v-kredit";

    private WebElement priceInput;

    private WebElement initialFeeInput;

    private WebElement selectTimeInput;

    private WebElement selectTimeOption;

    private WebElement submitButton;

    private WebElement finalPrice;

    private WebElement feeWarning;

    public CalculatorPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
    }

    public CalculatorPage openPage() {
        log.info("Opening VTB calculator page");
        driver.get(CALCULATOR_PAGE);
        return this;
    }

    public CalculatorPage sendKeysToForm(String productPrice, String initialFee) {
        log.info("Sending keys to VTB calculator");
        priceInput.sendKeys(productPrice);
        initialFeeInput.sendKeys(initialFee);
        selectTimeInput.click();
        selectTimeOption.click();
        return this;
    }

    public CalculatorPage submitCalculatorForm() {
        log.info("Submitting VTB calculator form");
        submitButton.click();
        return this;
    }

    public boolean getPresenceOfFeeWarning() {
        return feeWarning.isEnabled();
    }

    public String findFinalCalculatorPrice() {
        log.info("Getting final price from VTB calculator form");
        return finalPrice.getText();
    }

    protected void initializeFields(CalculatorTransfer transfer) {
        this.priceInput = transfer.priceInput;
        this.initialFeeInput = transfer.initialFeeInput;
        this.selectTimeInput = transfer.selectTimeInput;
        this.selectTimeOption = transfer.selectTimeOption;
        this.submitButton = transfer.submitButton;
        this.finalPrice = transfer.finalPrice;
        this.feeWarning = transfer.feeWarning;
    }
}
