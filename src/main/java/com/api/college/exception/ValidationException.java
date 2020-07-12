package com.api.college.exception;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@NoArgsConstructor
public class ValidationException extends RuntimeException implements BaseException {

    private boolean logMessage;
    private int code = 701;
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private Map body;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message, Throwable cause, boolean logMessage) {
        super(message, cause);
        this.logMessage = logMessage;
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public HttpStatus getHTTPStatus() {
        return status;
    }

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public boolean doLogMessage() {
        return logMessage;
    }

    @Override
    public Map getBody() {
        return body;
    }

    public static ValidationException withMessage(String message) {
        return new ValidationException(message);
    }

    public ValidationException andHTTPStatus(HttpStatus httpStatus) {
        this.status = httpStatus;
        return this;
    }

    public ValidationException andCode(int code) {
        this.code = code;
        return this;
    }

    public ValidationException andBody(Map body) {
        this.body = body;
        return this;
    }
}