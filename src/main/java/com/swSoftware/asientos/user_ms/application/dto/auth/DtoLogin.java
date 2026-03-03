package com.swSoftware.asientos.user_ms.application.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
@Schema(description = "DTO for user authentication credentials")
public record DtoLogin(
        @NotBlank
        @Schema(description = "Registered username", example = "jdoe_92")
        String username,

        @NotBlank
        @Schema(description = "Account password", example = "P@ssw0rd123!", format = "password")
        String password
) {
}
