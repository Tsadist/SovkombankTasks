package org.randomuser;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

public class RandomUsersTest extends BaseTest {

    private final static String queryValueGender = "male";
    private final static String queryNameGender = "gender";
    private final static String queryNameResults = "results";
    private final static int sizeRandomString = 10;
    private final static int validCountResults = 7;
    private final static int defaultCountResults = 1;
    private final static int minInvalidCountResults = -500;
    private final static int maxInvalidCountResults = 6000;

    @Test
    public void positiveWithoutParams() {
        ResponseBody.ResultModel[] result = requestWithoutParameters().getResults();
        Assertions.assertThat(result).hasSize(defaultCountResults);
    }

    @Test
    public void positiveWithGenderParameter() {
        ResponseBody.ResultModel[] result = requestWithQueryParams(queryNameGender, queryValueGender).getResults();
        Assertions.assertThat(result).hasSize(defaultCountResults);
        Arrays.stream(result)
                .forEach(resultModel -> Assertions.assertThat(resultModel.getGender()).isEqualTo(queryValueGender));
    }

    @Test
    public void positiveWithResultsParameter() {
        ResponseBody.ResultModel[] result = requestWithQueryParams(queryNameResults, String.valueOf(validCountResults)).getResults();
        Assertions.assertThat(result).hasSize(validCountResults);
    }

    @Test
    public void positiveWithTwoParameters() {
        ResponseBody.ResultModel[] result = requestWithQueryParams(Map
                .of(queryNameGender, queryValueGender, queryNameResults, String.valueOf(validCountResults)))
                .getResults();
        Assertions.assertThat(result).hasSize(validCountResults);
        Arrays.stream(result)
                .forEach(resultModel -> Assertions.assertThat(resultModel.getGender()).isEqualTo(queryValueGender));
    }

    @Test
    public void negativeWithMaxResultsParameter() {
        ResponseBody.ResultModel[] result = requestWithQueryParams(queryNameResults, String.valueOf(maxInvalidCountResults)).getResults();
        Assertions.assertThat(result).hasSize(defaultCountResults);
    }

    @Test
    public void negativeWithMinResultsParameter() {
        ResponseBody.ResultModel[] result = requestWithQueryParams(queryNameResults, String.valueOf(minInvalidCountResults)).getResults();
        Assertions.assertThat(result).hasSize(defaultCountResults);
    }

    @Test
    public void negativeWithBody() {
        ResponseBody.ResultModel[] result = requestWithBody(RandomStringUtils.random(sizeRandomString),
                RandomStringUtils.random(sizeRandomString)).getResults();
        Assertions.assertThat(result).hasSize(defaultCountResults);
    }

    @Test
    public void positiveWithInvalidGenderParameter() {
        ResponseBody.ResultModel[] result = requestWithQueryParams(queryNameGender, RandomStringUtils
                .random(sizeRandomString))
                .getResults();
        Assertions.assertThat(result).hasSize(defaultCountResults);
    }
}
