package com.swSoftware.asientos.user_ms.application.dto.role;

import com.swSoftware.asientos.user_ms.domain.status.StatusRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(description = "DTO for creating a new security role")
public record DtoCreateRole(
        @NotBlank
        @Schema(description = "Unique name of the role", example = "ADMIN")
        String name,

        @NotNull
        @Schema(description = "Initial status of the role", example = "ACTIVE")
        StatusRole status
) {
}