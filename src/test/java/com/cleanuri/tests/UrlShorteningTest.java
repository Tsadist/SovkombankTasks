package com.cleanuri.tests;

import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class UrlShorteningTest extends BaseTest {

    private final static String requestKey = "url";
    private final static String responseValue = "https://cleanuri.com/";
    private final static String responseKey = "result_url";
    private final static String errorResponseKey = "error";
    private final static int minLengthTooLongRequest = 3246;
    private final static int maxLengthTooLongRequest = 1672400;
    private final static int lengthInvalidRequestBodyKey = 5;

    @Test
    @Tag("positive")
    public void positiveTest() {
        RestAssured
                .given()
                .multiPart(requestKey, testDate.getUrl())
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(responseKey, Matchers.containsString(responseValue));
    }

    @Test
    @Tag("negative")
    public void emptyRequestBody() {
        errorResponse()
                .assertThat()
                .body(errorResponseKey, Matchers.equalTo(errorValues.getUrlIsEmpty()));
    }

    @Test
    @Tag("negative")
    public void invalidRequestBodyKey() {
        errorResponse(RandomStringUtils.random(new Random().nextInt(lengthInvalidRequestBodyKey)), testDate.getUrl())
                .assertThat()
                .body(errorResponseKey, Matchers.equalTo(errorValues.getUrlIsEmpty()));
    }

    @Test
    @Tag("negative")
    public void emptyRequestBodyValue() {
        errorResponse("")
                .assertThat()
                .body(errorResponseKey, Matchers.equalTo(errorValues.getUrlAfterSanitization()));
    }

    @Test
    @Tag("negative")
    public void invalidRequestBody() {
        errorResponse(RandomStringUtils.random(new Random().nextInt(minLengthTooLongRequest - 1)))
                .assertThat()
                .body(errorResponseKey, Matchers.equalTo(errorValues.getUrlInvalid()));
    }

    @Test
    @Tag("negative")
    public void soMuchRequestBodyForShorten() {
        errorResponse(RandomStringUtils
                .random(new Random()
                        .nextInt(maxLengthTooLongRequest - minLengthTooLongRequest) + minLengthTooLongRequest))
                .assertThat()
                .body(errorResponseKey, Matchers.equalTo(errorValues.getUrlTooLong()));
    }
}
