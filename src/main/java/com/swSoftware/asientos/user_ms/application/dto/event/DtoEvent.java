package com.swSoftware.asientos.user_ms.application.dto.event;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record DtoEvent<T>(
        String correlationId,
        Instant createdAt,
        T data
) {
}
