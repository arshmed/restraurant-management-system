package com.ros.orderservice.infrastructure.context;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserContext {

    private static final ThreadLocal<Long> userId = new ThreadLocal<>();
    private static final ThreadLocal<List<String>> roles = new ThreadLocal<>();

    public void setUserId(Long id) {
        userId.set(id);
    }

    public Long getUserId() {
        return userId.get();
    }

    public void setRoles(List<String> userRoles) {
        roles.set(userRoles);
    }

    public List<String> getRoles() {
        return roles.get();
    }

    public void clear() {
        userId.remove();
        roles.remove();
    }
}