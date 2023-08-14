package org.randomuser;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class BaseTest {

    private final static String baseUrl = "https://randomuser.me/api";

    @BeforeAll
    public static void before() {
        RestAssured.baseURI = baseUrl;
    }

    protected ResponseBody requestWithoutParameters() {
        Response response = RestAssured.given().get();
        response.then().statusCode(200);
        return JsonUtils.createModel(ResponseBody.class, response.getBody().asString());
    }

    protected ResponseBody requestWithQueryParams(Map<String, String> map) {
        Response response = RestAssured.given().queryParams(map).get();
        response.then().statusCode(200);
        return JsonUtils.createModel(ResponseBody.class, response.getBody().asString());
    }

    protected ResponseBody requestWithQueryParams(String nameParam, String valueParam) {
        return requestWithQueryParams(Map.of(nameParam, valueParam));
    }

    protected ResponseBody requestWithBody(String bodyKey, String bodyValue) {
        Response response = RestAssured.given().multiPart(bodyKey, bodyValue).post();
        response.then().statusCode(200);
        return JsonUtils.createModel(ResponseBody.class, response.getBody().asString());
    }
}
