package com.swSoftware.asientos.user_ms.domain.service.user;

import com.swSoftware.asientos.user_ms.application.dto.seatReserve.DtoSeatReserve;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionUserNotFound;
import com.swSoftware.asientos.user_ms.domain.model.EventProcessedModel;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import com.swSoftware.asientos.user_ms.domain.port.IEventProcessedService;
import com.swSoftware.asientos.user_ms.domain.port.IOutboxEventService;
import com.swSoftware.asientos.user_ms.domain.port.IUserService;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static com.swSoftware.asientos.user_ms.infrastructure.shared.LogMessages.*;

@Service
@AllArgsConstructor
@Slf4j
public class GeneralUserService implements IUserService {

    private final UserRepository userRepository;
    private final IOutboxEventService iOutboxEventService;
    private final IEventProcessedService iEventProcessedService;

    @Override
    public UserModel getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(ExceptionUserNotFound::new);
    }

    @Override
    @Transactional
    public void verifyUserToReserveSeat(DtoSeatReserve request){

        if(iEventProcessedService.eventAlreadyProcessed(request.idCorrelation())){
            log.warn(MESSAGE_EVENT_ALREADY_PROCESSED.toString());
            return;
        };

        if(!userRepository.existsById(request.idUser())){
            log.warn(MESSAGE_USER_NOT_FOUND.toString());
            throw new ExceptionUserNotFound();
        };

        iOutboxEventService.saveEvent(request,"dev.user-ms.reserve-seat.v2");
        iEventProcessedService.saveEventProcessed(EventProcessedModel.builder()
                        .id(request.idCorrelation())
                        .createdAt(Instant.now())
                        .data(request.toString())
                .build());
    }
}
