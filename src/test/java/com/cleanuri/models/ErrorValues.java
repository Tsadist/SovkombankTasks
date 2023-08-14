package com.cleanuri.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorValues {

    private String urlIsEmpty;
    private String urlAfterSanitization;
    private String urlInvalid;
    private String urlTooLong;

}
