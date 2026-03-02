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
import org.springframework.stereotype.Service;

import javax.management.relation.RoleStatus;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateRoleService implements UpdateRoleUseCase {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public DtoRole execute(Long id, DtoRoleUpdate request){
        RoleModel role = roleRepository.findById(id).orElseThrow(ExceptionRoleNotFound::new);

        if(roleRepository.existsByNameAndIdNot(request.name(),id)) throw new ExceptionNameRoleAlreadyInUse();
        roleMapper.toModelToUpdate(request,role);

        role.setDeleteAt(request.status().equals(StatusRole.DISABLED) ? Instant.now() : null);

        return roleMapper.toDto(roleRepository.save(role));
    }
}
