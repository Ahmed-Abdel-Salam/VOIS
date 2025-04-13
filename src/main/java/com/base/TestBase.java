package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.framework.ReportManager;
import com.framework.Waiting;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;


import java.time.Duration;

public class TestBase {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ExtentReports report;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        report = ReportManager.getInstance();
    }

    @AfterSuite
    public void flushReport() {
        if (report != null) {
            report.flush();
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setup(String url) {
        if (!url.startsWith("http")) {
            throw new IllegalArgumentException("URL must start with http:// or https://");
        }
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-notifications");
        driver.set(new ChromeDriver(options));

        WebDriverManager.chromedriver().setup();
        getDriver().get(url);

        Waiting.waitUntilElementIsVisible(
                By.tagName("body"),
                Duration.ofSeconds(30),
                getDriver()
        );

    }

    public void tearDown() {
        if (driver != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
