package com.tests;

import com.aventstack.extentreports.ExtentTest;
import com.base.TestBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.POM.YoutubeHomePage;
import com.POM.YoutubeResultPage;
import com.POM.YoutubeVideoPage;
import com.framework.ReportManager;
import com.framework.Waiting;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.TimerTask;

public class YoutubeSearch extends TestBase {

    private YoutubeHomePage youtubeHomePage;
    private YoutubeResultPage youtubeResultPage;
    private YoutubeVideoPage youtubeVideoPage;
    private final String searchKeyword = "Selenium Tutorial";

    @DataProvider(name = "Search Keywords")
    public Object[][] providerSearchKeywords() throws IOException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();

        URL resource = getClass().getClassLoader().getResource("testdata/SearchKeywords.json");
        if (resource == null) {
            throw new FileNotFoundException("File not found in classpath");
        }

        File jsonFile = new File(resource.toURI());
        JsonNode node = mapper.readTree(jsonFile);

        JsonNode keywordsNode = node.get(0);
        String keyword1 = keywordsNode.get("Search Keyword 1").asText();
        String keyword2 = keywordsNode.get("Search Keyword 2").asText();

        return new Object[][]{{keyword1}, {keyword2}};
    }

    @BeforeMethod
    public void setupTest() {
        setup("https://www.youtube.com");
        this.youtubeHomePage = new YoutubeHomePage(getDriver());
        this.youtubeResultPage = new YoutubeResultPage(getDriver());
        this.youtubeVideoPage = new YoutubeVideoPage(getDriver());
    }

    @Test(dataProvider = "Search Keywords")
    public void searchYoutubeAndAssertThirdResultIsCorrect(String keyword) {
        ExtentTest test = ReportManager.getInstance().createTest("Search Test: " + keyword);
        try {
            youtubeHomePage.searchYoutubeByKeyword(keyword);
            youtubeResultPage.clickOnFiltersButton();
            youtubeResultPage.clickOnFilterTypeVideo();
            String title1 = youtubeResultPage.getVideoTitleByIndex(2);
            youtubeResultPage.clickVideoByTitle(title1);
            String title2 = youtubeVideoPage.getTitle();
            youtubeVideoPage.assertThatTitlesAreEqual(title1, title2);
            test.pass("Test passed");
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void searchYoutubeAndAssertTenthResultIsCorrect() {
        ExtentTest test = ReportManager.getInstance().createTest("Search Test2: ");
        try {
            youtubeHomePage.searchYoutubeByKeyword(searchKeyword);
            youtubeResultPage.clickOnFiltersButton();
            youtubeResultPage.clickOnFilterTypeVideo();
            String title1 = youtubeResultPage.getVideoTitleByIndex(9);
            System.out.println("Title 1: " + title1);
            youtubeResultPage.clickVideoByTitle(title1);
            String title2 = youtubeVideoPage.getTitle();
            System.out.println("Title 2: " + title2);
            youtubeVideoPage.assertThatTitlesAreEqual(title1, title2);
            test.pass("Test passed");
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }

    }

    @AfterMethod
    public void tearDownTest() {
        tearDown();
    }
}
