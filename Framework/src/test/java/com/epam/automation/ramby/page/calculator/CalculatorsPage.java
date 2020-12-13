package com.epam.automation.ramby.page.calculator;

import com.epam.automation.ramby.page.CommonPage;
import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.WebDriver;

public abstract class CalculatorsPage extends CommonPage {
    protected static final String CALCULATOR_PAGE = "https://ram.by/komputery-v-kredit-noutbuki-v-kredit";

    public CalculatorsPage(WebDriver driver) {
        super(driver, LogProvider.getLog());
    }

    public <T extends CalculatorsPage> T openPage(Class<T> calculator) {
        log.info("Opening VTB calculator page");
        driver.get(CALCULATOR_PAGE);
        return (T)this;
    }
}
