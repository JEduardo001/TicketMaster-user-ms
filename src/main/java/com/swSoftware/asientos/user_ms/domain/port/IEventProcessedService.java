package com.swSoftware.asientos.user_ms.domain.port;

import com.swSoftware.asientos.user_ms.domain.model.EventProcessedModel;

import java.util.UUID;

public interface IEventProcessedService {
    void saveEventProcessed(EventProcessedModel request);
    boolean eventAlreadyProcessed(UUID id);
}
