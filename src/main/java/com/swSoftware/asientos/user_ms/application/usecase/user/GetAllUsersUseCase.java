package com.swSoftware.asientos.user_ms.application.usecase.user;

import com.swSoftware.asientos.user_ms.application.dto.page.DtoPage;

import java.util.UUID;

public interface GetAllUsersUseCase {
    DtoPage execute(UUID lastId, int limit);
}
