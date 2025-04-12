package com.tests;

import com.base.TestBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pages.YoutubeHomePage;
import com.pages.YoutubeResultPage;
import com.pages.YoutubeVideoPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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

        return new Object[][]{
                {keyword1}, {keyword2}
        };
    }

    @BeforeMethod
    public void setupTest(String browser) {
        setup(browser, "https://www.youtube.com");
        this.youtubeHomePage = new YoutubeHomePage(getDriver());
        this.youtubeResultPage = new YoutubeResultPage(getDriver());
        this.youtubeVideoPage = new YoutubeVideoPage(getDriver());
        System.out.println("Thread ID: " + Thread.currentThread().threadId());
    }

    @Test(dataProvider = "Search Keywords")
    public void searchYoutubeAndAssertThirdResultIsCorrect(String keyword) {
//        System.out.println("This is it: " + keyword);
        youtubeHomePage.searchYoutubeByKeyword(keyword);
        youtubeResultPage.clickOnFiltersButton();
        youtubeResultPage.clickOnFilterTypeVideo();
        String title1 = youtubeResultPage.getVideoTitleByIndex(2);
        youtubeResultPage.clickVideoByIndex(2);
        String title2 = youtubeVideoPage.getTitle();
        youtubeVideoPage.assertThatTitlesAreEqual(title1, title2);
    }

    @Test
    public void searchYoutubeAndAssertTenthResultIsCorrect() {
        youtubeHomePage.searchYoutubeByKeyword(searchKeyword);
        youtubeResultPage.clickOnFiltersButton();
        youtubeResultPage.clickOnFilterTypeVideo();
        String title1 = youtubeResultPage.getVideoTitleByIndex(9);
        youtubeResultPage.clickVideoByIndex(9);
        String title2 = youtubeVideoPage.getTitle();
        youtubeVideoPage.assertThatTitlesAreEqual(title1, title2);
    }

    @AfterMethod
    public void tearDownTest() {
        tearDown();
    }
}
