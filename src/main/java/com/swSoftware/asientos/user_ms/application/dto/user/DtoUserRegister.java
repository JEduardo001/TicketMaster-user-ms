package com.swSoftware.asientos.user_ms.application.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swSoftware.asientos.user_ms.domain.status.StatusUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;

@Builder
@Schema(description = "DTO for new user registration")
public record DtoUserRegister(
        @NotBlank
        @Schema(description = "Unique username for the account", example = "jdoe_92")
        String username,

        @NotBlank
        @Schema(description = "User's first name", example = "John")
        String name,

        @NotBlank
        @Schema(description = "Account password", example = "P@ssw0rd123!", format = "password")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_\\-]).{8,35}$", // 1 upperCase, 1 lower case, 1 number 1 symbol
                message = "Password must contain upper, lower, number and special character"

        )
        String password,

        @NotNull
        @Schema(description = "Initial account status", example = "ACTIVE")
        StatusUser status,

        @NotBlank
        @Schema(description = "Password confirmation field", example = "P@ssw0rd123!", format = "password")
        String passwordRepeat,

        @NotBlank
        @Schema(description = "User's last name", example = "Doe")
        String lastname,

        @Email
        @Schema(description = "Contact email address", example = "john.doe@example.com")
        String email,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @Schema(description = "User birth date (Format: yyyy-MM-dd)", example = "1992-05-15")
        LocalDate birthday
) {
}
