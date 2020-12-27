package com.backbase.kalah.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
public class ErrorResponse {

    @JsonInclude(NON_NULL)
    private String code;

    @JsonInclude(NON_NULL)
    private String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this(getErrorCode(httpStatus), message);
    }

    public static String getErrorCode(HttpStatus httpStatus) {
        return "HTTP-" + httpStatus.value();
    }
}
