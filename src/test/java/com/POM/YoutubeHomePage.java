package com.POM;

import com.base.TestBase;
import com.framework.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YoutubeHomePage extends TestBase {
    private final WebDriver driver;

    private final By youtubeSearch = By.xpath("//input[@placeholder=\"Search\"]");
    private final By searchButton = By.xpath("(//button[@aria-label=\"Search\"])[1]");

    public YoutubeHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchYoutubeByKeyword(String searchKeyword) {
        ElementActions.typeText(youtubeSearch, driver, searchKeyword);
        ElementActions.click(searchButton, driver);
    }
}
