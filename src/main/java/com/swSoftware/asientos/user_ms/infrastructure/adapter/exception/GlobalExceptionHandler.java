package com.swSoftware.asientos.user_ms.infrastructure.adapter.exception;

import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoErrorResponseApi;
import com.swSoftware.asientos.user_ms.application.exception.handler.ApplicationExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ApplicationExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(400).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DtoErrorResponseApi> handleTechnical(Exception ex) {
        return ResponseEntity.status(500).body(new DtoErrorResponseApi("SERVER_ERROR", 500, ""));
    }



}
