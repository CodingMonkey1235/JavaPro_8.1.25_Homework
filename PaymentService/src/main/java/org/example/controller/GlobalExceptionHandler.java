package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.commonError.CommonErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    CommonErrorResponseDto handleCommonException(Exception exception) {
        String errorMessage = exception.getMessage();
        log.error(errorMessage);
        return new CommonErrorResponseDto("ERROR", errorMessage);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    CommonErrorResponseDto handleNoResourceFoundException(Exception exception) {
        String errorMessage = exception.getMessage();
        log.error(errorMessage);
        return new CommonErrorResponseDto("ERROR", errorMessage);
    }
}
