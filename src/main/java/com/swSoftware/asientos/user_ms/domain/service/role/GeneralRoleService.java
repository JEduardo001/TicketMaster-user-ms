package com.swSoftware.asientos.user_ms.domain.service.role;

import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import com.swSoftware.asientos.user_ms.domain.port.IRoleService;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GeneralRoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleModel> getAllRoles(List<Long> rolesId){
        return roleRepository.findAllById(rolesId);
    }
}
