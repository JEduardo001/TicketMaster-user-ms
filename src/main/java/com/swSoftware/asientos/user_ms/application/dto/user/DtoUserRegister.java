package com.swSoftware.asientos.user_ms.application.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;

@Builder
public record DtoUserRegister(
        @NotBlank
        String username,
        @NotBlank
        String name,
        @NotBlank
        String password,
        @NotBlank
        String passwordRepeat,
        @NotBlank
        String lastname,
        @Email
        String email,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthday
) {
}
