package com.example.auth.service;

import com.example.auth.entity.JWTUser;

public interface AuthService {

    String getTokenByUser(JWTUser jwtUser);

    boolean validate(String token);

    String refresh(String oldToken);
}
