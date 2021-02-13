package ru.appline.sberFramework.utils;

import io.qameta.allure.Attachment;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static ru.appline.sberFramework.managers.DriverMngr.getDriver;

public class MyListener implements TestExecutionListener {
    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        addScreenshot1();
    }



    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] addScreenshot1() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
