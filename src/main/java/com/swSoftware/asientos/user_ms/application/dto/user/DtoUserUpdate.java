package com.swSoftware.asientos.user_ms.application.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swSoftware.asientos.user_ms.domain.status.StatusUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DtoUserUpdate(

        @Size(min = 3, max = 20)
        String username,
        @Size(min = 2, max = 50)
        String name,
        @Size(min = 2, max = 50)
        String lastname,
        @Email
        String email,
        StatusUser status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthday
) {
}
