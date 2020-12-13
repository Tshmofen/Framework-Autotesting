package com.epam.automation.ramby.provider;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverProvider {
    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        // support for no-display linux instances on ci
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1280,720");
        return new ChromeDriver(chromeOptions);
    }

    public static void shutDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}
