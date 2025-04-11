package com.pages;

import com.base.TestBase;
import com.framework.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YoutubeResultPage extends TestBase {
    private final WebDriver driver;
    private final By filterButton = By.xpath("//button[@aria-label=\"Search filters\"]");
    private final By typeVideo = By.xpath("//div[@title=\"Search for Video\"]");
    private final By thirdVideoToClick = By.xpath("(//a[@id=\"video-title\"])[3]");

    public YoutubeResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFiltersButton() {
        ElementActions.click(filterButton, driver);
    }

    public void clickOnFilterTypeVideo() {
        ElementActions.click(typeVideo, driver);
    }

    public String getThirdVideoTitle() {
        return ElementActions.getText(thirdVideoToClick, driver);
    }

    public void clickThirdVideo(By locator) {
        ElementActions.click(locator, driver);
    }

    public By getVideoTitleXPath(String videoTitle) {
        return By.xpath("//a[@title=\"" + videoTitle + "\"]");
    }
}
