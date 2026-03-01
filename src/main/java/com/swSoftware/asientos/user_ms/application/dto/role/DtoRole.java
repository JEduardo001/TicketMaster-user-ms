package com.swSoftware.asientos.user_ms.application.dto.role;

import com.swSoftware.asientos.user_ms.domain.status.StatusRole;
import lombok.Builder;

@Builder
public record DtoRole(
        Integer id,
        String name,
        StatusRole status

) {
}
