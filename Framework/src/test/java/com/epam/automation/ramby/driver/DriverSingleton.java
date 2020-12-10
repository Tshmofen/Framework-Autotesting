package com.epam.automation.ramby.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSingleton {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            // support for no-display linux instances on ci
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("disable-gpu");
            chromeOptions.addArguments("window-size=1280,720");
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

    public static void shutDriver() {
        driver.quit();
        driver = null;
    }
}
