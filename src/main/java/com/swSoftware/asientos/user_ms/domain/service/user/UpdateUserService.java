package com.swSoftware.asientos.user_ms.domain.service.user;

import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserUpdate;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionUserNotFound;
import com.swSoftware.asientos.user_ms.application.usecase.user.UpdateUserUseCase;
import com.swSoftware.asientos.user_ms.domain.exception.user.ExceptionEmailAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import com.swSoftware.asientos.user_ms.domain.port.IRoleService;
import com.swSoftware.asientos.user_ms.domain.status.StatusUser;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.userMapper.UserMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.swSoftware.asientos.user_ms.infrastructure.shared.LogMessages.*;

@Service
@AllArgsConstructor
@Slf4j
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final IRoleService iRoleService;

    @Override
    public DtoUser execute(UUID id, DtoUserUpdate request){
        UserModel user = userRepository.findById(id).orElseThrow(ExceptionUserNotFound::new);

        if(userRepository.existsByEmailAndIdNot(request.email(),id)){
            log.warn(MESSAGE_USER_EMAIL_ALREADY_IN_USE.toString());
            throw new ExceptionEmailAlreadyInUse();
        }
        if(userRepository.existsByUsernameAndIdNot(request.username(),id)){
            log.warn(MESSAGE_USER_USERNAME_ALREADY_IN_USE.toString());
            throw new ExceptionEmailAlreadyInUse();
        }

        userMapper.toEntityToUpdate(request,user);

        if(request.status() != null){
            user.setDeleteAt(request.status().equals(StatusUser.DISABLED) ? Instant.now() : null);
        }

        if(request.idRoles() != null){
            List<RoleModel> roles = iRoleService.getAllRoles(request.idRoles());
            user.setRoles(roles);
        }

        UserModel s = userRepository.save(user);
        log.info(MESSAGE_USER_UPDATED.toString());

        return userMapper.toDto(s);
    }
}
