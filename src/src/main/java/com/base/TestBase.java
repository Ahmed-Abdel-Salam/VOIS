package com.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class TestBase {
    //    protected WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected static final int IMPLICIT_WAIT = 10;
    protected static final int PAGE_LOAD_TIMEOUT = 30;

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setup(String browserName, String url) {
        if (!url.startsWith("http")) {
            throw new IllegalArgumentException("URL must start with http:// or https://");
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--disable-notifications");
            driver.set(new ChromeDriver(options));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized", "--disable-notifications");
            driver.set(new FirefoxDriver(options));
        }
        WebDriverManager.chromedriver().setup();
        getDriver().get(url);
//        driver.get(url);
    }

    public void tearDown() {
        if (driver != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
