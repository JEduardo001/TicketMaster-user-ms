package com.swSoftware.asientos.user_ms.application.dto.role;

import com.swSoftware.asientos.user_ms.domain.status.StatusRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "DTO representing role information")
public record DtoRole(
        @Schema(description = "Unique identifier of the role", example = "1")
        Long id,

        @Schema(description = "Name of the role", example = "ADMIN")
        String name,

        @Schema(description = "Current status of the role", example = "ACTIVE")
        StatusRole status
) {
}