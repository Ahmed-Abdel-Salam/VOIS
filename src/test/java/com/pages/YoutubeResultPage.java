package com.pages;

import com.base.TestBase;
import com.framework.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YoutubeResultPage extends TestBase {
    private final WebDriver driver;
    private final By filterButton = By.xpath("//button[@aria-label=\"Search filters\"]");
    private final By typeVideo = By.xpath("//div[@title=\"Search for Video\"]");
    private final By thirdVideoToClick = By.xpath("(//a[@id=\"video-title\"])[3]");
    private final By videoElementPath = By.xpath("//ytd-video-renderer//a[@id='video-title']");

    public YoutubeResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFiltersButton() {
        ElementActions.click(filterButton, driver);
    }

    public void clickOnFilterTypeVideo() {
        ElementActions.click(typeVideo, driver);
    }

//    public By getVideoByIndex(int index) {
//        return By.xpath("(//a[@id='video-title'])[" + index + "]");
//    }

    public String getVideoTitleByIndex(int index) {
        WebElement element = ElementActions.getElementsByIndex(videoElementPath, driver, index);
        return element.getAttribute("title");
    }

    public void clickVideoByIndex(int index) {
        ElementActions.getElementsByIndexAndClick(videoElementPath, driver, index);
    }

    public By getVideoTitleXPath(String videoTitle) {
        return By.xpath("//a[@title=\"" + videoTitle + "\"]");
    }
}
