package com.swSoftware.asientos.user_ms.application.usecase.role;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;

import java.util.UUID;

public interface GetRoleUseCase {
    DtoRole execute(Long role);
}
