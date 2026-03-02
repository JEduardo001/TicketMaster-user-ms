package com.swSoftware.asientos.user_ms.application.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record DtoLogin(
        @NotBlank
        String username,
        @NotBlank
        String password

) {
}
