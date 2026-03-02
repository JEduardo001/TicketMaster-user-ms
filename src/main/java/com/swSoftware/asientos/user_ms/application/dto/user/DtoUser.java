package com.swSoftware.asientos.user_ms.application.dto.user;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.domain.status.StatusUser;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record DtoUser(
        UUID id,
        String username,
        String name,
        String lastname,
        String email,
        StatusUser status,
        LocalDate birthday,
        List<DtoRole> roles,
        Instant createdAt,
        Instant deleteAt
) {
}
