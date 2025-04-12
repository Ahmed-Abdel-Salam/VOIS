package com.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ElementActions {

    private final static Duration duration = Duration.ofSeconds(30);

    public static void click(By locator, WebDriver driver) {
        try {
            Waiting.waitUntilElementIsClickable(locator, duration, driver);
            WebElement element = driver.findElement(locator);
            element.click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(locator).click();
        }
    }

    public static WebElement getElementsByIndex(By locator, WebDriver driver, int index) {
        List<WebElement> elements = driver.findElements(locator);
        return elements.get(index);
    }

    public static void getElementsByIndexAndClick(By locator, WebDriver driver, int index) {
        List<WebElement> elements = driver.findElements(locator);
        elements.get(index).click();
    }

    public static void typeText(By locator, WebDriver driver, String text) {
        Waiting.waitUntilElementIsClickable(locator, duration, driver);
        WebElement element = driver.findElement(locator);
        element.sendKeys(text);
    }

    public static String getText(By locator, WebDriver driver) {
        Waiting.waitUntilElementIsVisible(locator, duration, driver);
        WebElement element = driver.findElement(locator);
        return element.getText();
    }
}
