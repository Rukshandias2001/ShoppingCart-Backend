package com.example.ShoppingCart.Config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.List;

public class CustomJwtAuthenticationConverter extends JwtAuthenticationConverter {


    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        String role = jwt.getClaimAsString("role");

        String mappedRole = switch (role) {
            case "1" -> "ROLE_ADMIN";
            case "2" -> "ROLE_MANAGER";
            case "3" -> "ROLE_CUSTOMER";
            default -> "ROLE_UNKNOWN";
        };

        // 👇 Add this line here
        System.out.println("Mapped Role: " + mappedRole);

        return List.of(new SimpleGrantedAuthority(mappedRole));
    }
}
