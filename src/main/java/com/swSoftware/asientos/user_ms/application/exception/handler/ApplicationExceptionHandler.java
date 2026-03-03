package com.swSoftware.asientos.user_ms.application.exception.handler;

import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoErrorResponseApi;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionRoleNotFound;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionUserNotFound;
import com.swSoftware.asientos.user_ms.domain.exception.handler.DomainExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.swSoftware.asientos.user_ms.domain.common.HeaderConstants.CORRELATION_KEY;


@Slf4j
public abstract class ApplicationExceptionHandler extends DomainExceptionHandler {

    private String getIdCorrelation(){
        return MDC.get(CORRELATION_KEY.toString());
    }

    @ExceptionHandler(ExceptionUserNotFound.class)
    public ResponseEntity<DtoErrorResponseApi> handleNotFound(ExceptionUserNotFound ex) {
        return ResponseEntity.status(404).body(new DtoErrorResponseApi("NOT_FOUND_USER", 404, getIdCorrelation()));
    }

    @ExceptionHandler(ExceptionRoleNotFound.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionRoleNotFound(ExceptionRoleNotFound ex) {
        return ResponseEntity.status(404).body(new DtoErrorResponseApi("NOT_FOUND_ROLE", 404, getIdCorrelation()));
    }
}
