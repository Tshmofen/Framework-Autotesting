package com.epam.automation.ramby.test;

import com.epam.automation.ramby.provider.DriverProvider;
import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class CommonDriverTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserDriverSetup() {
        LogProvider.getLog().info("Initialize browser");
        driver = DriverProvider.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void browserDriverShutDown(){
        LogProvider.getLog().info("Shut down browser");
        DriverProvider.shutDriver();
    }
}
