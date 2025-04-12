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
//    private final String searchKeyword = "Selenium Tutorial";

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
    public void setupTest() {
        setup("https://www.youtube.com");
        this.youtubeHomePage = new YoutubeHomePage(driver);
        this.youtubeResultPage = new YoutubeResultPage(driver);
        this.youtubeVideoPage = new YoutubeVideoPage(driver);
    }

    @Test(dataProvider = "Search Keywords")
    public void searchYoutubeAndAssertResultIsCorrect(String keyword) {
//        System.out.println("This is it: " + keyword);
        youtubeHomePage.searchYoutubeByKeyword(keyword);
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
