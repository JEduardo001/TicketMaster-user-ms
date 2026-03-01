package com.swSoftware.asientos.user_ms.application.usecase.user;

import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserUpdate;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UpdateUserUseCase {
    @Transactional
    DtoUser execute(UUID id, DtoUserUpdate request);
}
