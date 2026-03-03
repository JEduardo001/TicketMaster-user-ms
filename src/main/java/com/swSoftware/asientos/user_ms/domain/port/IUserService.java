package com.swSoftware.asientos.user_ms.domain.port;

import com.swSoftware.asientos.user_ms.application.dto.seatReserve.DtoSeatReserve;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
    UserModel getUserByUsername(String username);
    void verifyUserToReserveSeat(DtoSeatReserve request);
}
