package com.swSoftware.asientos.user_ms.domain.service.user;

import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserRegister;
import com.swSoftware.asientos.user_ms.application.usecase.user.RegisterUserUseCase;
import com.swSoftware.asientos.user_ms.domain.exception.user.ExceptionEmailAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.exception.user.ExceptionPasswordDoNotMatch;
import com.swSoftware.asientos.user_ms.domain.exception.user.ExceptionUsernameAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.userMapper.UserMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@AllArgsConstructor
@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DtoUser execute(DtoUserRegister request){

        if(!request.password().equals(request.passwordRepeat())){
            throw new ExceptionPasswordDoNotMatch();
        }

        if(userRepository.existsByEmail(request.email())){
            throw new ExceptionEmailAlreadyInUse();
        }

        if(userRepository.existsByUsername(request.username())){
            throw new ExceptionUsernameAlreadyInUse();
        }

        UserModel newUser = userMapper.toEntityToCreate(request);
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setCreatedAt(Instant.now());

        return userMapper.toDto(userRepository.save(newUser));
    }

}
