package com.oursummer.vacit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 모든 예외를 처리하기 위한 핸들러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        Map<String, String> errorDetails = Map.of(
                "timestamp", new Date().toString(),
                "message", ex.getMessage(),
                "details", request.getDescription(false)
        );
        log.error("{}: {}", ex.getClass().getName(), ex.getMessage());
        log.error("GlobalExceptionHandler", ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
