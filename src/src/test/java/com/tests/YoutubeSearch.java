package com.tests;

import com.base.TestBase;
import com.pages.YoutubeHomePage;
import com.pages.YoutubeResultPage;
import com.pages.YoutubeVideoPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class YoutubeSearch extends TestBase {

    private YoutubeHomePage youtubeHomePage;
    private YoutubeResultPage youtubeResultPage;
    private YoutubeVideoPage youtubeVideoPage;
    private final String searchKeyword = "Selenium Tutorial";

    @BeforeMethod
    public void setupTest() {
        setup("https://www.youtube.com");
        this.youtubeHomePage = new YoutubeHomePage(driver);
        this.youtubeResultPage = new YoutubeResultPage(driver);
        this.youtubeVideoPage = new YoutubeVideoPage(driver);
    }

    @Test
    public void searchYoutubeAndAssertResultIsCorrect() {
        youtubeHomePage.searchYoutubeByKeyword(searchKeyword);
        youtubeResultPage.clickOnFiltersButton();
        youtubeResultPage.clickOnFilterTypeVideo();
        String title1 = youtubeResultPage.getThirdVideoTitle();
        youtubeResultPage.clickThirdVideo(youtubeResultPage.getVideoTitleXPath(title1));
        String title2 = youtubeVideoPage.getTitle();
        youtubeVideoPage.assertThatTitlesAreEqual(title1, title2);
    }

    @AfterMethod
    public void tearDownTest() {
        tearDown();
    }
}
