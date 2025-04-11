package com.pages;

import com.framework.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class YoutubeVideoPage {
    private final WebDriver driver;
    By videoTitle = By.xpath("(//yt-formatted-string[contains(@class, 'ytd-watch-metadata')])[1]");

    public YoutubeVideoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return ElementActions.getText(videoTitle, driver);
    }

    public void assertThatTitlesAreEqual(String title1, String title2) {
        Assert.assertEquals(title1, title2);
    }
}
