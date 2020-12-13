package com.epam.automation.ramby.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class CommonPage {
    protected static final String SITE_URL = "https://ram.by/";
    protected static final long TIMEOUT = Duration.ofSeconds(10).getSeconds();
    protected final WebDriver driver;
    protected final Logger log;

    public CommonPage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }
}
