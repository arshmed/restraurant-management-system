package com.ros.userservice.application.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}