package com.swSoftware.asientos.user_ms.infrastructure.config.service;


import com.swSoftware.asientos.user_ms.domain.model.RoleModel;
import com.swSoftware.asientos.user_ms.domain.model.UserModel;
import com.swSoftware.asientos.user_ms.domain.service.user.GeneralUserService;
import com.swSoftware.asientos.user_ms.domain.service.user.GetUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsSerivceImpl implements UserDetailsService {

    private final GeneralUserService generalUserService;

    @Override
    public UserDetails loadUserByUsername(String username){
        UserModel user = generalUserService.getUserByUsername(username);

        List<String> rolesList = user.getRoles().stream()
                .map(RoleModel::getName)
                .toList();

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(rolesList.toArray(new String[0]))
                .build();

    }
}