package com.epam.automation.ramby.test;

import com.epam.automation.ramby.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class CommonDriverTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserDriverSetup() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void browserDriverShutDown(){
        DriverSingleton.shutDriver();
    }
}
