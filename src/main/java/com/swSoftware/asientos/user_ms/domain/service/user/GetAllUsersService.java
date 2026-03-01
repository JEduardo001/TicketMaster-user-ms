package com.swSoftware.asientos.user_ms.domain.service.user;

import com.swSoftware.asientos.user_ms.application.dto.page.DtoPage;
import com.swSoftware.asientos.user_ms.application.dto.user.DtoUser;
import com.swSoftware.asientos.user_ms.application.usecase.user.GetAllUsersUseCase;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.mapper.userMapper.UserMapper;
import com.swSoftware.asientos.user_ms.infrastructure.adapter.output.persistence.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllUsersService implements GetAllUsersUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public DtoPage execute(UUID lastId, int limit) {
        Pageable limitProvider = PageRequest.of(0, limit);

        List<UserModel> users = userRepository.findNextPage(lastId, limitProvider);
        List<DtoUser> usersDto = users.stream().map(userMapper::toDto).collect(Collectors.toList());
        String nextCursor = users.isEmpty() ? null : users.get(users.size() - 1).getId().toString();

        return new DtoPage(nextCursor,users.size() == limit,usersDto);
    }

}
