package com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.RoleMapper;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    DtoRole toDto(RoleModel request);
}
