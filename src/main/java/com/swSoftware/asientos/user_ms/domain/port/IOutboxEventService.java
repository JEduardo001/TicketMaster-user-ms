package com.swSoftware.asientos.user_ms.domain.port;



public interface IOutboxEventService<T> {
    void saveEvent(T request,String nameTopic);
}
