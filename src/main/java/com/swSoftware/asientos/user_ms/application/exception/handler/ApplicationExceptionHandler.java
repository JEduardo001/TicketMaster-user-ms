package com.swSoftware.asientos.user_ms.application.exception.handler;

import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoErrorResponseApi;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionRoleNotFound;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionUserNotFound;
import com.swSoftware.asientos.user_ms.domain.exception.handler.DomainExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public abstract class ApplicationExceptionHandler extends DomainExceptionHandler {
    @ExceptionHandler(ExceptionUserNotFound.class)
    public ResponseEntity<DtoErrorResponseApi> handleNotFound(ExceptionUserNotFound ex) {
        return ResponseEntity.status(404).body(new DtoErrorResponseApi("NOT_FOUND_USER", 404, ""));
    }

    @ExceptionHandler(ExceptionRoleNotFound.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionRoleNotFound(ExceptionRoleNotFound ex) {
        return ResponseEntity.status(404).body(new DtoErrorResponseApi("NOT_FOUND_ROLE", 404, ""));
    }
}
