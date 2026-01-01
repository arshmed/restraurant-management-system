package com.ros.userservice.application.mapper;

import com.ros.userservice.application.dto.LoginRequestDto;
import com.ros.userservice.application.dto.RegisterRequestDto;
import com.ros.userservice.domain.service.AuthService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthService.RegisterUserCommand registerDtoToCommand(RegisterRequestDto dto);
    AuthService.LoginUserCommand loginDtoToCommand(LoginRequestDto dto);
}