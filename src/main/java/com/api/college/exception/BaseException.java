package com.api.college.exception;
import org.springframework.http.HttpStatus;

import java.util.Map;

public interface BaseException {
    int DATA_CONVERSION_ERROR = 601;

    default HttpStatus getHTTPStatus() {
        return HttpStatus.OK;
    }

    int getErrorCode();

    String getMessage();

    default Map getBody() {
        return null;
    }

    default boolean doLogMessage() {
        return false;
    }

}
