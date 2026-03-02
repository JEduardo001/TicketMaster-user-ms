package com.swSoftware.asientos.user_ms.domain.service.role;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionRoleNotFound;
import com.swSoftware.asientos.user_ms.application.exception.ExceptionUserNotFound;
import com.swSoftware.asientos.user_ms.application.usecase.role.GetRoleUseCase;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.RoleMapper.RoleMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetRoleService implements GetRoleUseCase {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public DtoRole execute(Long id){
        return roleMapper.toDto(roleRepository.findById(id).orElseThrow(ExceptionRoleNotFound::new));
    }
}
