package com.swSoftware.asientos.user_ms.application.dto.responseApi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "API response specific to the login process")
public record DtoResponseApiLogIn(
        @Schema(description = "HTTP status code", example = "200")
        Integer status,

        @Schema(description = "Correlation ID for request tracing", example = "550e8400-e29b-41d4-a716-446655440000")
        String idCorrelation,

        @Schema(description = "Generated JWT authentication token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token
) {
}
