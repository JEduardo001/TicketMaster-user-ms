package com.swSoftware.asientos.user_ms.application.dto.role;

import com.swSoftware.asientos.user_ms.domain.status.StatusRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "DTO for updating role details")
public record DtoRoleUpdate(
        @Schema(description = "Updated name for the role", example = "MODERATOR")
        String name,

        @Schema(description = "Updated status for the role", example = "INACTIVE")
        StatusRole status
) {
}