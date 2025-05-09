package com.tests;

import com.BE.ApiPage;
import com.aventstack.extentreports.ExtentTest;
import com.base.TestBase;
import com.framework.JsonActions;
import com.framework.ReportManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class APITest extends TestBase {
    ApiPage page = new ApiPage();


    private static final Random random = new Random();

    private static int getRandomUserId() {
        return random.nextInt(10) + 1;
    }

    @Test
    public void testingRandomUsers() {
        test = report.createTest("testingRandomUsers");
        try {
            int userID = getRandomUserId();
            Response response = page.getUserById(userID);
            String userEmailAddress = JsonActions.getJsonResponseAsValue(response, "email");
            System.out.println("User Email Address: " + userEmailAddress);
            Response posts = page.getAllPostsForSpecificUser(userID);
            List<Integer> postIds = JsonActions.getJsonResponseAsList(posts, "id");
            for (int postID : postIds) {
                Assert.assertTrue(postID >= 0 && postID <= 100);
                System.out.println("Post ID is valid: " + postID);
            }
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            throw e;
        }
    }
}
