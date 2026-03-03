package com.swSoftware.asientos.user_ms.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "eventProcessed_table")
public class EventProcessedModel {

    @Id
    private UUID id;
    @Column(columnDefinition = "TEXT")
    private String data;
    private Instant createdAt;
}
