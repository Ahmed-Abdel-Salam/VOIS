package com.framework;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestActions {

    /**
     * @param targetPortal The URL to hit, which can be Themis or Harpocrates.
     * @param endPoint     The endpoint appended on the URL to hit.
     * @param formParams   To send the request with form parameters
     * @param contentType  Use contentType.ANY when not sending form parameters or JSON.
     *                     Use contentType.URLENC when sending form parameters.
     *                     Use contentType.JSON when sending JSON Object.
     * @return Building the request specs
     */
    private static RequestSpecification prepareRequestSpecification(String targetPortal, String endPoint, List<List<Object>> queryParams,
                                                                    List<List<Object>> formParams, Map<String, String> headers, ContentType contentType) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(String.valueOf(targetPortal));
        builder.setBasePath(endPoint);
        builder.setContentType((contentType.toString()));

        if (formParams != null) {
            formParams.forEach(param -> {
                builder.addFormParam(param.get(0).toString(), param.get(1));
            });
        }
        if (queryParams != null) {
            queryParams.forEach(param -> {
                builder.addQueryParam(param.get(0).toString(), param.get(1));
            });
        }

        if (headers != null && !headers.isEmpty()) {
            headers.forEach(builder::addHeader);
        }
        builder.setRelaxedHTTPSValidation();
        return builder.build();
    }

    /**
     * @param endPoint    The endpoint to hit which is appended on target portal
     * @param requestType HTTP Method type, example: POST, GET, DELETE, PUT
     * @param JSONObject  JSON body to send in request if exist
     * @return Returns response as Response
     */
    public static Response sendRequest(String targetPortal, String endPoint, requestType requestType, String JSONObject, List<List<Object>> formParams,
                                       List<List<Object>> queryParams, Map<String, String> headers, ContentType contentType) {
        Response response;

        System.out.println("Start performing API request on: [" + endPoint + "].");
        RequestSpecification request;
        if (JSONObject != null) {
            request = given().spec(prepareRequestSpecification(targetPortal, endPoint, formParams, queryParams, headers, contentType)).body(JSONObject);
        } else {
            request = given().spec(prepareRequestSpecification(targetPortal, endPoint, formParams, queryParams, headers, contentType));
        }

        switch (requestType) {
            case POST:
                response = request.when().post().then().extract().response();
                System.out.println("Response: [" + response.asString() + "].");
                return response;
            case PUT:
                response = request.when().put().then().extract().response();
                System.out.println("Response: [" + response.asString() + "].");
                return response;
            case GET:
                response = request.when().get().then().extract().response();
                System.out.println("Response: [" + response.asString() + "].");
                return response;
            case DELETE:
                response = request.when().delete().then().extract().response();
                System.out.println("Response: [" + response.asString() + "].");
                return response;
            default:
                break;
        }
        System.out.println("Response is null.");
        return null;
    }


    /**
     * Adding enumerations to handle only the possible values that can be provided
     */
    public enum requestType {
        POST, GET, DELETE, PUT
    }
}
