package com.project.Capstone.auth.dto;

import lombok.Data;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
