package com.swSoftware.asientos.user_ms.domain.port;

import com.swSoftware.asientos.user_ms.domain.model.RoleModel;

import java.util.List;

public interface IRoleService {
    List<RoleModel> getAllRoles(List<Long> rolesId);
}
