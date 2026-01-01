package com.ros.restaurantservice.infrastructure.context;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserContextFilter implements Filter {

    private final UserContext userContext;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String userIdHeader = request.getHeader("X-User-Id");
        String userRolesHeader = request.getHeader("X-User-Roles");

        if (userIdHeader != null && !userIdHeader.isEmpty()) {
            userContext.setUserId(Long.parseLong(userIdHeader));
        }

        if (userRolesHeader != null && !userRolesHeader.isEmpty()) {
            userContext.setRoles(Arrays.asList(userRolesHeader.split(",")));
        } else {
            userContext.setRoles(Collections.emptyList());
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            userContext.clear();
        }
    }
}