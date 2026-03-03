package com.swSoftware.asientos.user_ms.application.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swSoftware.asientos.user_ms.domain.status.StatusUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
@Schema(description = "DTO for updating existing user information")
public record DtoUserUpdate(

        @Size(min = 3, max = 20)
        @Schema(description = "Updated unique username", example = "jdoe_updated", minLength = 3, maxLength = 20)
        String username,

        @Size(min = 2, max = 50)
        @Schema(description = "Updated first name", example = "Johnny", minLength = 2, maxLength = 50)
        String name,

        @Size(min = 2, max = 50)
        @Schema(description = "Updated last name", example = "Smith", minLength = 2, maxLength = 50)
        String lastname,

        @Email
        @Schema(description = "Updated contact email", example = "johnny.smith@example.com")
        String email,

        @Schema(description = "New account status", example = "ACTIVE")
        StatusUser status,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @Schema(description = "User's birth date (Format: yyyy-MM-dd)", example = "1992-05-15")
        LocalDate birthday,

        @Schema(description = "List of role IDs to be assigned to the user", example = "[1, 2, 3]")
        List<Long> idRoles
) {
}
