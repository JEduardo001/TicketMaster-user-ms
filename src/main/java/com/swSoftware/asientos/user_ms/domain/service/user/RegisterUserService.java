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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.swSoftware.asientos.user_ms.infrastructure.shared.LogMessages.*;

@AllArgsConstructor
@Service
@Slf4j
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DtoUser execute(DtoUserRegister request){

        if(!request.password().equals(request.passwordRepeat())){
            log.warn(MESSAGE_PASSWORD_DO_NOT_MATCH.toString());
            throw new ExceptionPasswordDoNotMatch();
        }

        if(userRepository.existsByEmail(request.email())){
            log.warn(MESSAGE_USER_EMAIL_ALREADY_IN_USE.toString());
            throw new ExceptionEmailAlreadyInUse();
        }

        if(userRepository.existsByUsername(request.username())){
            log.warn(MESSAGE_USER_USERNAME_ALREADY_IN_USE.toString());
            throw new ExceptionUsernameAlreadyInUse();
        }

        UserModel newUser = userMapper.toEntityToCreate(request);
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setCreatedAt(Instant.now());

        UserModel user = userRepository.save(newUser);
        DtoUser response = userMapper.toDto(user);

        log.info(MESSAGE_USER_SAVED.toString());
        return response;
    }

}
