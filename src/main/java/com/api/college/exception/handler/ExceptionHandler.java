package com.api.college.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.college.exception.BaseException;
import com.api.college.exception.ExceptionResponse;
import com.api.college.exception.ValidationException;

@RestControllerAdvice("com.api")
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ExceptionResponse> handleError(BaseException e) {
        ExceptionResponse errorResponse = new ExceptionResponse(e.getErrorCode(), e.getMessage(),e.getBody());
        ResponseEntity<ExceptionResponse> entity = new ResponseEntity<>(errorResponse, e.getHTTPStatus());
        if (e.doLogMessage()) {
            logger.error(e.getMessage(), e);
        } else
            logger.info(e.getMessage());
        return entity;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e) {
        ExceptionResponse errorResponse = new ExceptionResponse(500, "Server Error");
        ResponseEntity<ExceptionResponse> entity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        logger.error(e.getMessage(), e);
        return entity;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        ExceptionResponse errorResponse = new ExceptionResponse(500, "Server Error");
        ResponseEntity<ExceptionResponse> entity = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        logger.error(e.getMessage(), e);
        return entity;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return super.handleExceptionInternal(ex, errorMessages, headers, HttpStatus.BAD_REQUEST, request);
    }
}
