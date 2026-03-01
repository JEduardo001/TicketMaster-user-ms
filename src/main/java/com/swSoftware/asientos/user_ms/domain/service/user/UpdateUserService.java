package com.swSoftware.asientos.user_ms.domain.service.user;

import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserUpdate;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionUserNotFound;
import com.swSoftware.asientos.user_ms.application.usecase.user.UpdateUserUseCase;
import com.swSoftware.asientos.user_ms.domain.exception.ExceptionEmailAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import com.swSoftware.asientos.user_ms.domain.status.StatusUser;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.userMapper.UserMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public DtoUser execute(UUID id, DtoUserUpdate request){
        UserModel user = userRepository.findById(id).orElseThrow(ExceptionUserNotFound::new);

        if(userRepository.existsByEmailAndIdNot(request.email(),id)) throw new ExceptionEmailAlreadyInUse();
        if(userRepository.existsByUsernameAndIdNot(request.username(),id)) throw new ExceptionEmailAlreadyInUse();

        user.setDeleteAt(request.status().equals(StatusUser.DISABLED) ? Instant.now() : null);

        userMapper.toEntityToUpdate(request,user);
        return userMapper.toDto(user);
    }
}
