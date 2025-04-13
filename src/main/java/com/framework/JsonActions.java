package com.framework;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

public class JsonActions {
    public static String getJsonResponseAsValue(Response response, String jsonPath) {
        try {
            return response.jsonPath().getString(jsonPath);
        } catch (Exception e) {
            System.out.println("Error extracting value with path: " + jsonPath);
            return null;
        }
    }

    public static <T> List<T> getJsonResponseAsList(Response response, String jsonPath) {
        try {
            return response.jsonPath().getList(jsonPath);
        } catch (Exception e) {
            System.out.println("Error extracting list with path: " + jsonPath);
            return Collections.emptyList();
        }
    }
}

