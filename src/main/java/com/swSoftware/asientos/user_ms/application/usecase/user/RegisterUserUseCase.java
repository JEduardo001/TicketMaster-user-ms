package com.swSoftware.asientos.user_ms.application.usecase.user;

import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserRegister;
import org.springframework.transaction.annotation.Transactional;

public interface RegisterUserUseCase {
    @Transactional
    DtoUser execute(DtoUserRegister request);
}

