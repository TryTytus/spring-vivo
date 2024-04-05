package com.example.demo.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@RestResource()
@SecurityRequirement(name = "apiKey")
public interface UserRepo extends JpaRepository<User, String> {
    User findByUsername(String username);

}
