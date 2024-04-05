package com.example.demo.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("resourceAuth")
public class ResourceAuth {
    public boolean checkResourceOwnership(String userN) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return username.equals(userN);
    }
}

