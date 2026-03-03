package com.swSoftware.asientos.user_ms.domain.service.outboxEvent;

import com.swSoftware.asientos.user_ms.domain.model.OutboxEventModel;
import com.swSoftware.asientos.user_ms.domain.port.IOutboxEventService;
import com.swSoftware.asientos.user_ms.domain.status.StatusEvent;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.kafka.KafkaProducer;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.OutboxEventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static com.swSoftware.asientos.user_ms.domain.common.HeaderConstants.CORRELATION_KEY;
import static com.swSoftware.asientos.user_ms.infrastructure.shared.LogMessages.*;
import static org.springframework.kafka.support.KafkaHeaders.CORRELATION_ID;


@Service
@AllArgsConstructor
@Slf4j
public class OutboxEventService<T> implements IOutboxEventService<T> {

    private final OutboxEventRepository outboxEventRepository;
    private final KafkaProducer kafkaProducer;

    @Override
    public void saveEvent(T request,String nameTopic){

        outboxEventRepository.save(OutboxEventModel.builder()
                .correlationId(MDC.get(CORRELATION_KEY.toString()))
                .createAt(Instant.now())
                .nameTopic(nameTopic)
                .status(StatusEvent.PENDING)
                .retryCount(0)
                .payload(request.toString())
                .build());
        log.info(MESSAGE_EVENT_SAVED.toString());

    }

    @Scheduled(fixedDelay = 500)
    public void publishPendingEvents() {
        List<OutboxEventModel> events = outboxEventRepository.findAllByStatus(StatusEvent.PENDING);

        for (OutboxEventModel e : events) {
            try {

                kafkaProducer.send(e.getPayload(),e.getNameTopic(), e.getCorrelationId());
                e.setStatus(StatusEvent.SENT);
                outboxEventRepository.save(e);
                log.info(MESSAGE_SEND_EVENT.toString(), e.getId());

            } catch (Exception ex) {

                e.setRetryCount(e.getRetryCount() + 1);
                if (e.getRetryCount() > 20) {
                    log.error(MESSAGE_ERROR_SEND_EVENT.toString(), ex);
                    e.setStatus(StatusEvent.FAILED);
                    kafkaProducer.publisFailedSendEventDlq(e.getPayload());
                }

                outboxEventRepository.save(e);
            }
        }
    }
}
