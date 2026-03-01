package com.swSoftware.asientos.user_ms.application.dto.page;

import lombok.Builder;

import java.util.List;

@Builder
public record DtoPage<T>(
        String nextCursor,
        boolean hasNext,
        List<T> elements
) {
}
