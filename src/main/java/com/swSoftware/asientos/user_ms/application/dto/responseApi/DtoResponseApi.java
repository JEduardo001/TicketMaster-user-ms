package com.swSoftware.asientos.user_ms.application.dto.responseApi;

import lombok.Builder;

@Builder
public record DtoResponseApi<T>(
        String message,
        Integer status,
        String idCorrelation,
        T data
) {
}
