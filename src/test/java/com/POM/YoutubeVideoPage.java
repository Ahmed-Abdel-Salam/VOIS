package com.POM;

import com.framework.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class YoutubeVideoPage {
    private final WebDriver driver;
    By videoTitle = By.xpath("//yt-formatted-string[@title][.//ancestor::div[@id='title']]");

    public YoutubeVideoPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        WebElement element = ElementActions.getElement(videoTitle, driver);
        return element.getAttribute("title");
    }

    public void assertThatTitlesAreEqual(String title1, String title2) {
        Assert.assertEquals(title1, title2);
    }
}
