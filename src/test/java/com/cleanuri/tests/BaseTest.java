package com.cleanuri.tests;

import com.cleanuri.JsonUtils;
import com.cleanuri.models.ErrorValues;
import com.cleanuri.models.TestDate;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    private final static String testDateFilePath = "src/test/resources/testDate.json";
    private final static String errorValuesFilePath = "src/test/resources/errorValues.json";

    private final static String baseUrl = "https://cleanuri.com/api/v1/shorten";
    private final static String requestKey = "url";

    protected static TestDate testDate;
    protected static ErrorValues errorValues;

    @BeforeAll
    public static void before() {
        testDate = JsonUtils.createModel(TestDate.class, testDateFilePath);
        errorValues = JsonUtils.createModel(ErrorValues.class, errorValuesFilePath);
        RestAssured.baseURI = baseUrl;
    }

    public ValidatableResponse errorResponse(String requestValue) {
        return RestAssured.given()
                .multiPart(requestKey, requestValue)
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    public ValidatableResponse errorResponse() {
        return RestAssured.given()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    public ValidatableResponse errorResponse(String requestKey, String requestValue) {
        return RestAssured.given()
                .multiPart(requestKey, requestValue)
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


}
