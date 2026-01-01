package com.ros.userservice.application.controller;

import com.ros.userservice.application.dto.AuthResponseDto;
import com.ros.userservice.application.dto.LoginRequestDto;
import com.ros.userservice.application.dto.RegisterRequestDto;
import com.ros.userservice.application.mapper.AuthMapper;
import com.ros.userservice.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthMapper authMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto request) {
        authService.registerUser(authMapper.registerDtoToCommand(request));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register-owner")
    public ResponseEntity<?> registerOwner(@RequestBody RegisterRequestDto request) {
        authService.registerOwner(authMapper.registerDtoToCommand(request));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        String token = authService.loginUser(authMapper.loginDtoToCommand(request));
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}