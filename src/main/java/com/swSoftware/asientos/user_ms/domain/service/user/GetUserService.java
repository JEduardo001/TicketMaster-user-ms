package com.swSoftware.asientos.user_ms.domain.service.user;

import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionUserNotFound;
import com.swSoftware.asientos.user_ms.application.usecase.user.GetUserUseCase;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.userMapper.UserMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetUserService implements GetUserUseCase {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public DtoUser execute(UUID id){
        return userMapper.toDto(userRepository.findById(id).orElseThrow(ExceptionUserNotFound::new));
    }
}
