package com.swSoftware.asientos.user_ms.domain.exception.handler;

import com.swSoftware.asientos.user_ms.application.dto.responseApi.DtoErrorResponseApi;
import com.swSoftware.asientos.user_ms.domain.exception.role.ExceptionNameRoleAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.exception.user.ExceptionEmailAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.exception.user.ExceptionPasswordDoNotMatch;
import com.swSoftware.asientos.user_ms.domain.exception.user.ExceptionUsernameAlreadyInUse;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.swSoftware.asientos.user_ms.domain.common.HeaderConstants.CORRELATION_KEY;

public abstract class DomainExceptionHandler {

    private String getIdCorrelation(){
        return MDC.get(CORRELATION_KEY.toString());
    }

    @ExceptionHandler(ExceptionEmailAlreadyInUse.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionEmailAlreadyInUse(ExceptionEmailAlreadyInUse ex) {
        return ResponseEntity.status(422).body(new DtoErrorResponseApi("EMAIL_IN_USE", 422, getIdCorrelation()));
    }

    @ExceptionHandler(ExceptionUsernameAlreadyInUse.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionUsernameAlreadyInUse(ExceptionUsernameAlreadyInUse ex) {
        return ResponseEntity.status(422).body(new DtoErrorResponseApi("USERNAME_IN_USE", 422, getIdCorrelation()));
    }

    @ExceptionHandler(ExceptionPasswordDoNotMatch.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionPasswordDoNotMatch(ExceptionPasswordDoNotMatch ex) {
        return ResponseEntity.status(422).body(new DtoErrorResponseApi("PASSWORD_DO_NOT_MATCH", 422, getIdCorrelation()));
    }

    @ExceptionHandler(ExceptionNameRoleAlreadyInUse.class)
    public ResponseEntity<DtoErrorResponseApi> ExceptionNameRoleAlreadyInUse(ExceptionNameRoleAlreadyInUse ex) {
        return ResponseEntity.status(422).body(new DtoErrorResponseApi("NAME_ROLE_ALREADY_IN_USE", 422, getIdCorrelation()));
    }



}
