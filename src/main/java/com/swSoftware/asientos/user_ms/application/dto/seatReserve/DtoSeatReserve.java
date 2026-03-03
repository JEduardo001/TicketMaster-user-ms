package com.swSoftware.asientos.user_ms.application.dto.seatReserve;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record DtoSeatReserve(
        @NotNull
        UUID idCorrelation,
        @NotNull
        List<UUID> idsSeat,
        @NotNull
        UUID idUser

) {
}
