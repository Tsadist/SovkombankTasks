package org.randomuser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    public static <T> T createModel(Class<? extends T> clazz, String jsonResult) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(jsonResult, clazz);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;

        }
    }
}
