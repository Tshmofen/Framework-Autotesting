package com.epam.automation.ramby.page.calculator;

import com.epam.automation.ramby.page.AbstractPage;
import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class CalculatorsPage extends AbstractPage {
    protected static final String CALCULATOR_PAGE = "https://ram.by/komputery-v-kredit-noutbuki-v-kredit";

    protected WebElement priceInput;

    protected WebElement initialFeeInput;

    protected WebElement selectTimeInput;

    protected WebElement selectTimeOption;

    protected WebElement submitButton;

    protected WebElement finalPrice;

    protected WebElement feeWarning;

    public CalculatorsPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
    }
}
