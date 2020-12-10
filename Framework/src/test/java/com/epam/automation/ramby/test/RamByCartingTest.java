package com.epam.automation.ramby.test;

import com.epam.automation.ramby.page.RamByCartingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RamByCartingTest {
    private WebDriver driver;

    @DataProvider
    public Object[][] cartingData() {
        return new Object[][]{
                {"https://ram.by/blok-pitanija-650w-thermaltake-smart-pro-rgb-ps-spr-0650fpcbeu-r.html"},
                {"https://ram.by/kompjuter-ofisnyj-s-monitorom-22-na-baze-processora-amd-a6-9500-1.html"}
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

    @Test(dataProvider = "cartingData")
    public void addProductToCartTest(String productPage) {
        boolean productPresence = new RamByCartingPage(driver, productPage)
                .openProductPage()
                .addProductToCart()
                .openCartPage()
                .isProductInCart();

        Assert.assertTrue(productPresence);
    }

    @AfterMethod(alwaysRun = true)
    public void browserShutDown(){
        driver.quit();
    }
}
