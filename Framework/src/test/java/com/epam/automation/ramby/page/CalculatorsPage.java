package com.epam.automation.ramby.page;

import com.epam.automation.ramby.provider.LogProvider;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class CalculatorsPage {
    protected static final String CALCULATOR_PAGE = "https://ram.by/komputery-v-kredit-noutbuki-v-kredit";
    protected final WebDriver driver;
    protected final Logger log;

    public CalculatorsPage(WebDriver driver) {
        this.driver = driver;
        this.log = LogProvider.getLog();
    }

    public <T extends CalculatorsPage> T openPage(Class<T> calculator) {
        log.info("Opening VTB calculator page");
        driver.get(CALCULATOR_PAGE);
        return (T)this;
    }
}
