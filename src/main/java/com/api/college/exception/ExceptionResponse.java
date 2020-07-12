package com.api.college.exception;
import java.util.Map;

import com.api.college.exception.serializer.ExceptionBSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.NoArgsConstructor;
@NoArgsConstructor
@JsonSerialize(using = ExceptionBSerializer.class)
public class ExceptionResponse {
    private int errorCode;
    private String message;
    private Map<String, Object> body;

    public ExceptionResponse(int errorCode, String message, Map<String, Object> body) {
        this.errorCode = errorCode;
        this.message = message;
        this.body = body;
    }

    public ExceptionResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Map getBody() {
        return body;
    }
}
