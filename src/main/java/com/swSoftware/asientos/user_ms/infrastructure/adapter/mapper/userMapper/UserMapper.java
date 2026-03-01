package com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.userMapper;

import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserRegister;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUserUpdate;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.RoleMapper.RoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {RoleMapper.class})
public interface UserMapper {
    DtoUser toDto(UserModel user);
    UserModel toEntityToCreate(DtoUserRegister request);
    @Mapping(target = "id", ignore = true)
    void toEntityToUpdate(DtoUserUpdate newData, @MappingTarget UserModel actualData);
}