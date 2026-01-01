package com.ros.userservice.domain.service;

import com.ros.userservice.domain.model.User;

public interface AuthService {

    User registerUser(RegisterUserCommand command);

    User registerOwner(RegisterUserCommand command);

    String loginUser(LoginUserCommand command);

    record RegisterUserCommand(String name, String email, String password) {}
    record LoginUserCommand(String email, String password) {}
}