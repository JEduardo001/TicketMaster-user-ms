package com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.RoleMapper;

import com.swSoftware.asientos.user_ms.application.dto.role.DtoCreateRole;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRoleUpdate;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserUpdate;
import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    DtoRole toDto(RoleModel request);
    List<DtoRole> toDtoList(List<RoleModel> roles);
    RoleModel toModelToCreate(DtoCreateRole request);
    @Mapping(target = "id", ignore = true)
    void toModelToUpdate(DtoRoleUpdate newData, @MappingTarget RoleModel request);
}
