package com.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiting {
    public static void waitUntilElementIsClickable(By locator, Duration timeoutDuration, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitUntilElementIsVisible(By locator, Duration timeoutDuration, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutDuration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
