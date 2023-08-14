package com.cleanuri;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    public static <T> T createModel(Class<? extends T> clazz, String filePath) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;

        }
    }
}
