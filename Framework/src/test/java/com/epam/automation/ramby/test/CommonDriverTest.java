package com.epam.automation.ramby.test;

import com.epam.automation.ramby.listener.TestScreenshotListener;
import com.epam.automation.ramby.provider.DriverProvider;
import com.epam.automation.ramby.provider.LogProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestScreenshotListener.class})
public abstract class CommonDriverTest {
    // providing different drivers for different thread
    protected DriverProvider driverProvider;

    public CommonDriverTest() {
        driverProvider = new DriverProvider();
    }

    @BeforeMethod(alwaysRun = true)
    public void browserDriverSetup() {
        LogProvider.getLog().info("Initialize browser");
        driverProvider.updateContextDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void browserDriverShutDown(){
        LogProvider.getLog().info("Shut down browser");
        driverProvider.shutContextDriver();
    }
}
