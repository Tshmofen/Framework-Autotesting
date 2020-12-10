package com.epam.automation.ramby.listener;

import com.epam.automation.ramby.provider.DriverProvider;
import com.epam.automation.ramby.provider.LogProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        LogProvider.getLog().info("Making screenshot");
        this.saveScreenshot();
    }

    private void saveScreenshot() {
        File screenshot = ((TakesScreenshot) DriverProvider.getDriver())
                .getScreenshotAs(OutputType.FILE);
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH-mm-ss");
            String savePath = "./target/screenshot/" + dtf.format(LocalDateTime.now()) + ".png";
            FileUtils.copyFile(screenshot, new File(savePath));
        }
        catch (IOException e) {
            LogProvider.getLog().error("Can't save screenshot");
        }
    }
}
