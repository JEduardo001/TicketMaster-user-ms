package com.swSoftware.asientos.user_ms.domain.exception.handler;

import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoErrorResponseApi;
import com.swSoftware.asientos.user_ms.domain.exception.ExceptionEmailAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.exception.ExceptionPasswordDoNotMatch;
import com.swSoftware.asientos.user_ms.domain.exception.ExceptionUsernameAlreadyInUse;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class DomainExceptionHandler {

    @ExceptionHandler(ExceptionEmailAlreadyInUse.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionEmailAlreadyInUse(ExceptionEmailAlreadyInUse ex) {
        return ResponseEntity.status(422).body(new DtoErrorResponseApi("EMAIL_IN_USE", 422, ""));
    }

    @ExceptionHandler(ExceptionUsernameAlreadyInUse.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionUsernameAlreadyInUse(ExceptionUsernameAlreadyInUse ex) {
        return ResponseEntity.status(422).body(new DtoErrorResponseApi("USERNAME_IN_USE", 422, ""));
    }

    @ExceptionHandler(ExceptionPasswordDoNotMatch.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionPasswordDoNotMatch(ExceptionPasswordDoNotMatch ex) {
        return ResponseEntity.status(422).body(new DtoErrorResponseApi("Password_do_not_match", 422, ""));
    }



}
