package com.epam.automation.ramby.test;

import com.epam.automation.ramby.listener.TestScreenshotListener;
import com.epam.automation.ramby.provider.DriverProvider;
import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestScreenshotListener.class})
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
