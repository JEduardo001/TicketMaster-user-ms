package com.swSoftware.asientos.user_ms.domain.service.user;

import com.swSoftware.asientos.user_ms.application.exception.ExceptionUserNotFound;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import com.swSoftware.asientos.user_ms.domain.port.IUserService;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GeneralUserService implements IUserService {

    public final UserRepository userRepository;

    @Override
    public UserModel getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(ExceptionUserNotFound::new);
    }
}
