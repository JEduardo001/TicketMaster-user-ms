package com.swSoftware.asientos.user_ms.application.dto.responseApi;

import lombok.Builder;

@Builder
public record DtoResponseApiLogIn(
        String message,
        Integer status,
        String idCorrelation,
        String token
) {
}
