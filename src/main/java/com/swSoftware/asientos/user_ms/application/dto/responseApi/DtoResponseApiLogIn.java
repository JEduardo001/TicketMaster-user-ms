package com.swSoftware.asientos.user_ms.application.dto.responseApi;

import lombok.Builder;

@Builder
public record DtoResponseApiLogIn(
        Integer status,
        String idCorrelation,
        String token
) {
}
