package com.ros.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter authFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // 1. User Service (Kimlik Doğrulama)
                .route("user-service", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8084"))

                // 2. Restaurant Service
                .route("restaurant-service", r -> r.path("/api/v1/restaurants/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8081"))

                // 3. Order Service
                .route("order-service", r -> r.path("/api/v1/orders/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8082"))

                // 4. Reporting Service
                .route("reporting-service", r -> r.path("/api/v1/reports/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8083"))
                
                .build();
    }
}