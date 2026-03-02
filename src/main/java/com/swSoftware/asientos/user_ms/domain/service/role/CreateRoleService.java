package com.swSoftware.asientos.user_ms.domain.service.role;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoCreateRole;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.application.usecase.role.CreateRoleUseCase;
import com.swSoftware.asientos.user_ms.domain.exception.role.ExceptionNameRoleAlreadyInUse;
import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.RoleMapper.RoleMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class CreateRoleService implements CreateRoleUseCase {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public DtoRole execute(DtoCreateRole request){
        if(roleRepository.existsByName(request.name())){
            throw new ExceptionNameRoleAlreadyInUse();
        }

        RoleModel role = roleMapper.toModelToCreate(request);
        role.setCreateAt(Instant.now());
        return roleMapper.toDto(roleRepository.save(role));
    }
}
