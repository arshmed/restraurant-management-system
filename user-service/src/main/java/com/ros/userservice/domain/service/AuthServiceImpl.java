package com.ros.userservice.domain.service;

import com.ros.userservice.domain.exception.UserAlreadyExistsException;
import com.ros.userservice.domain.model.Role;
import com.ros.userservice.domain.model.User;
import com.ros.userservice.domain.repository.UserRepository;
import com.ros.userservice.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public User registerUser(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException("Email already in use: " + command.email());
        }
        User user = new User(
                command.name(),
                command.email(),
                passwordEncoder.encode(command.password())
        );

        user.setRoles(Set.of(Role.ROLE_CUSTOMER));

        return userRepository.save(user);
    }

    @Override
    public User registerOwner(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException("Email already in use: " + command.email());
        }
        User user = new User(
                command.name(),
                command.email(),
                passwordEncoder.encode(command.password())
        );

        user.setRoles(Set.of(Role.ROLE_RESTAURANT_OWNER));

        return userRepository.save(user);
    }

    @Override
    public String loginUser(LoginUserCommand command) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.email(),
                        command.password()
                )
        );
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new IllegalStateException("User not found after auth"));
        return jwtUtil.generateToken(user);
    }
}