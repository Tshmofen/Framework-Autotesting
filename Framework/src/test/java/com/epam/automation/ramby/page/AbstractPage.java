package com.epam.automation.ramby.page;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage {
    protected static final String SITE_URL = "https://ram.by/";
    protected static final long TIMEOUT = Duration.ofSeconds(10).getSeconds();
    protected final WebDriver driver;
    protected final Logger log;

    public AbstractPage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    public void waitForElementPresence(By selector) {
        log.info("Waiting for element: " + selector.toString());
        new WebDriverWait(driver, TIMEOUT)
                .until(
                        ExpectedConditions.presenceOfElementLocated(
                               selector
                        )
                );
    }

    public List<WebElement> waitForAllElementPresence(By selector) {
        log.info("Waiting for all elements: " + selector.toString());
       return new WebDriverWait(driver, TIMEOUT)
                .until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(
                                selector
                        )
                );
    }
}
