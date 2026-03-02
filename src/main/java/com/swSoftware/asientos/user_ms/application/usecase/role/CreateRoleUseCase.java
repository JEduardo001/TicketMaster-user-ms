package com.swSoftware.asientos.user_ms.application.usecase.role;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoCreateRole;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;

public interface CreateRoleUseCase {
    DtoRole execute(DtoCreateRole request);
}
