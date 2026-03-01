package com.swSoftware.asientos.user_ms.application.usecase.user;

import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;

import java.util.UUID;

public interface GetUserUseCase {
    DtoUser execute(UUID id);



}
