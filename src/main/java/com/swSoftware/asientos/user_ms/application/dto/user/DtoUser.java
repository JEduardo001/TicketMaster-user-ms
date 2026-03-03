package com.swSoftware.asientos.user_ms.application.dto.user;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.domain.status.StatusUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Builder
@Schema(description = "DTO representing full user information")
public record DtoUser(
        @Schema(description = "Unique identifier of the user", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Unique username", example = "jdoe_92")
        String username,

        @Schema(description = "User's first name", example = "John")
        String name,

        @Schema(description = "User's last name", example = "Doe")
        String lastname,

        @Schema(description = "User's email address", example = "john.doe@example.com")
        String email,

        @Schema(description = "Current account status", example = "ACTIVE")
        StatusUser status,

        @Schema(description = "User's birth date", example = "1992-05-15")
        LocalDate birthday,

        @Schema(description = "List of roles assigned to the user")
        List<DtoRole> roles,

        @Schema(description = "Timestamp when the user was created", example = "2023-10-01T10:00:00Z")
        Instant createdAt,

        @Schema(description = "Timestamp when the user was deleted (null if active)", example = "null")
        Instant deleteAt
) {
}