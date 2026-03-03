package com.swSoftware.asientos.user_ms.application.dto.seatReserve;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
@Schema(description = "DTO for processing seat reservation requests")
public record DtoSeatReserve(
        @NotNull
        @Schema(description = "Unique correlation ID to track the transaction across microservices", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID idCorrelation,

        @NotNull
        @Schema(description = "List of unique identifiers for the seats to be reserved", example = "[\"a1b2c3d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6\"]")
        List<UUID> idsSeat,

        @NotNull
        @Schema(description = "Unique identifier of the user making the reservation", example = "z9y8x7w6-v5u4-t3s2-r1q0-p9o8n7m6l5k4")
        UUID idUser
) {
}
