package com.swSoftware.asientos.user_ms.domain.port;

import com.swSoftware.asientos.user_ms.domain.model.UserModel;

public interface IUserService {
    UserModel getUserByUsername(String username);
}
