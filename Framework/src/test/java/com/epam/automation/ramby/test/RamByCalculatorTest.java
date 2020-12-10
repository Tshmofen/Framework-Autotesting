package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.RamByVTBCalculatorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class RamByCalculatorTest {
    public WebDriver driver;

    @DataProvider
    public Object[][] calculatorVTBData() {
        return new Object[][]{
                {"2500", "500", "2 854.19 руб."},
                {"1500", "0", "1 765.65 руб."}
        };
    }

    @BeforeMethod(alwaysRun = true)
    public void browserDriverSetup() {
        // support for linux instances on ci
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("window-size=1280,720");
        driver = new ChromeDriver(chromeOptions);
    }

    @Test(dataProvider = "calculatorVTBData")
    public void calculatorVTBPriceCorrectnessTest(String productPrice, String initialFee, String expectedPrice) {
        String finalPrice = new RamByVTBCalculatorPage(driver)
                .openPage()
                .sendKeysToVTBForm(productPrice, initialFee)
                .submitVTBCalculatorForm()
                .findFinalVTBCalculatorPrice();

        Assert.assertEquals(finalPrice, expectedPrice);
    }

    @AfterMethod(alwaysRun = true)
    public void browserShutDown(){
        driver.quit();
    }
}
