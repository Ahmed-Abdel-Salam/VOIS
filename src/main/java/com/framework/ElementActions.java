package com.framework;

import org.openqa.selenium.*;

import java.time.Duration;
import java.util.List;

public class ElementActions {

    private final static Duration duration = Duration.ofSeconds(30);

    public static void click(By locator, WebDriver driver) {
        Waiting.waitUntilElementIsClickable(locator, duration, driver);
        try {
            WebElement element = driver.findElement(locator);
            element.click();
        } catch (StaleElementReferenceException e) {
            WebElement element = driver.findElement(locator);
            element.click();
        }
    }

    public static WebElement getElementsByIndex(By locator, WebDriver driver, int index) {
        Waiting.waitUntilElementIsClickable(locator, duration, driver);
        List<WebElement> elements = driver.findElements(locator);
        return elements.get(index);
    }

    public static void getElementsByIndexAndClick(By locator, WebDriver driver, int index) {
        Waiting.waitUntilElementIsClickable(locator, duration, driver);
        List<WebElement> elements = driver.findElements(locator);
        WebElement element = elements.get(index);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element);

        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';",
                    element
            );
        }
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
