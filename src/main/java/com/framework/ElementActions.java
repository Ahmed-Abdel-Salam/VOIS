package com.framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementActions {

    private final static Duration duration = Duration.ofSeconds(45);

    public static void click(By locator, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);

            element.click();

        } catch (StaleElementReferenceException e) {
            System.out.println("[WARN] Stale element. Retrying with fresh reference.");
            retryClickWithJS(locator, driver);

        } catch (ElementClickInterceptedException e) {
            System.out.println("[WARN] Element click intercepted. Retrying via JS.");
            retryClickWithJS(locator, driver);

        } catch (ElementNotInteractableException e) {
            System.out.println("[WARN] Element not interactable. Retrying via JS.");
            retryClickWithJS(locator, driver);
        }
    }

    private static void retryClickWithJS(By locator, WebDriver driver) {
        try {
            WebElement fallbackElement = new WebDriverWait(driver, duration).until(ExpectedConditions.presenceOfElementLocated(locator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", fallbackElement);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallbackElement);

        } catch (Exception e) {
            throw new RuntimeException("Failed to click element via JS after fallback: " + locator, e);
        }
    }


    public static WebElement getElement(By locator, WebDriver driver) {
        Waiting.waitUntilElementIsVisible(locator, duration, driver);
        return driver.findElement(locator);
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.height='auto'; arguments[0].style.visibility='visible';", element);
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
