package com.swSoftware.asientos.user_ms.application.usecase.role;

import com.swSoftware.asientos.user_ms.application.dto.page.DtoPage;

public interface GetAllRolesUseCase {
    DtoPage execute(Long lastId, int limit);
}
