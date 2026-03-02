package com.swSoftware.asientos.user_ms.application.usecase.role;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRoleUpdate;

import java.util.UUID;

public interface UpdateRoleUseCase {
    DtoRole execute(Long id, DtoRoleUpdate request);
}
