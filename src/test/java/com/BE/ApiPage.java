package com.BE;

import com.framework.RestActions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiPage {

    String targetPortal = "https://jsonplaceholder.typicode.com/";

    public Response getUserById(int userID) {
        return RestActions.sendRequest(targetPortal, "users/" + userID
                , RestActions.requestType.GET,
                null,
                null, null, null, ContentType.ANY);
    }

    public Response getAllPostsForSpecificUser(int userID) {
        return RestActions.sendRequest(targetPortal, "users/" + userID + "/posts"
                , RestActions.requestType.GET,
                null,
                null, null, null, ContentType.ANY);
    }
}
