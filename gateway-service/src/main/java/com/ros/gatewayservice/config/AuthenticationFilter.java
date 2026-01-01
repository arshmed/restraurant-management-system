package com.ros.gatewayservice.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouterValidator validator;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (validator.isSecured.test(request)) {

                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return this.onError(exchange, "Missing Authorization header", HttpStatus.UNAUTHORIZED);
                }

                String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    return this.onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
                }

                String token = authHeader.substring(7);

                try {
                    Claims claims = jwtUtil.extractAllClaims(token);
                    String userId = claims.get("userId").toString();

                    List<String> rolesList = claims.get("roles", List.class);
                    String roles = rolesList.stream().map(Object::toString).collect(Collectors.joining(","));

                    ServerHttpRequest newRequest = request.mutate()
                            .header("X-User-Id", userId)
                            .header("X-User-Roles", roles)
                            .build();

                    return chain.filter(exchange.mutate().request(newRequest).build());
                } catch (Exception e) {
                    System.out.println("JWT ERROR: " + e.getMessage());
                    e.printStackTrace();
                    return this.onError(exchange, e.getMessage(), HttpStatus.UNAUTHORIZED);
                }

            }

            return chain.filter(exchange);
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    public static class Config {
    }
}