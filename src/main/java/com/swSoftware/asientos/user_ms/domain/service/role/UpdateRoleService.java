package com.swSoftware.asientos.user_ms.domain.service.role;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRoleUpdate;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionRoleNotFound;
import com.swSoftware.asientos.user_ms.application.usecase.role.UpdateRoleUseCase;
import com.swSoftware.asientos.user_ms.domain.exception.role.ExceptionNameRoleAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import com.swSoftware.asientos.user_ms.domain.status.StatusRole;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.RoleMapper.RoleMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleStatus;
import java.time.Instant;
import java.util.UUID;

import static com.swSoftware.asientos.user_ms.infrastructure.shared.LogMessages.MESSAGE_NAME_ROLE_ALREADY_IN_USE;
import static com.swSoftware.asientos.user_ms.infrastructure.shared.LogMessages.MESSAGE_ROLE_SAVED;

@Service
@AllArgsConstructor
@Slf4j
public class UpdateRoleService implements UpdateRoleUseCase {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public DtoRole execute(Long id, DtoRoleUpdate request){
        RoleModel role = roleRepository.findById(id).orElseThrow(ExceptionRoleNotFound::new);

        if(roleRepository.existsByNameAndIdNot(request.name(),id)){
            log.warn(MESSAGE_NAME_ROLE_ALREADY_IN_USE.toString());
            throw new ExceptionNameRoleAlreadyInUse();
        }
        roleMapper.toModelToUpdate(request,role);

        role.setDeleteAt(request.status().equals(StatusRole.DISABLED) ? Instant.now() : null);
        roleRepository.save(role);
        log.info(MESSAGE_NAME_ROLE_ALREADY_IN_USE.toString());

        return roleMapper.toDto(role);
    }
}
