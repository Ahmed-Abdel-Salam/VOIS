package com.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    protected static final int IMPLICIT_WAIT = 10;
    protected static final int PAGE_LOAD_TIMEOUT = 30;

    public WebDriver getDriver() {
        return driver;
    }

    public void setup(String url) {
        if (!url.startsWith("http")) {
            throw new IllegalArgumentException("URL must start with http:// or https://");
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-notifications");
        // options.addArguments("--headless"); // for headless execution
        driver = new ChromeDriver(options);
        setDriverTimeouts();
        driver.get(url);
    }

    private void setDriverTimeouts() {
        driver.manage().timeouts().
                implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT)).
                pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
