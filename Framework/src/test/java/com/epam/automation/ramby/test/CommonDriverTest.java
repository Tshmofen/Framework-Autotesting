package com.epam.automation.ramby.test;

import com.epam.automation.ramby.listener.TestScreenshotListener;
import com.epam.automation.ramby.provider.DriverProvider;
import com.epam.automation.ramby.provider.LogProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.HashMap;

@Listeners({TestScreenshotListener.class})
public abstract class CommonDriverTest {
    // stores thread id and driver for that thread
    protected HashMap<Long, WebDriver> drivers;

    @BeforeMethod(alwaysRun = true)
    public void browserDriverSetup() {
        if (drivers == null) {
            drivers = new HashMap<>();
        }
        LogProvider.getLog().info("Initialize browser");
        drivers.put(Thread.currentThread().getId(), DriverProvider.getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void browserDriverShutDown(){
        LogProvider.getLog().info("Shut down browser");
        long threadId = Thread.currentThread().getId();
        WebDriver currentDriver = drivers.get(threadId);
        if (currentDriver != null) {
            DriverProvider.shutDriver(currentDriver);
            drivers.remove(threadId);
        }
    }
}
