package com.swSoftware.asientos.user_ms.domain.service.role;

import com.swSoftware.asientos.user_ms.application.dto.page.DtoPage;
import com.swSoftware.asientos.user_ms.application.dto.role.DtoRole;
import com.swSoftware.asientos.user_ms.application.usecase.role.GetAllRolesUseCase;
import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.RoleMapper.RoleMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GetAllRolesService implements GetAllRolesUseCase {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public DtoPage execute(Long lastId, int limit){
        Pageable limitProvider = PageRequest.of(0, limit);

        List<RoleModel> users = roleRepository.findNextPage(lastId, limitProvider);
        List<DtoRole> usersDto = users.stream().map(roleMapper::toDto).collect(Collectors.toList());
        String nextCursor = users.isEmpty() ? null : users.get(users.size() - 1).getId().toString();

        return new DtoPage(nextCursor,users.size() == limit,usersDto);
    }
}
