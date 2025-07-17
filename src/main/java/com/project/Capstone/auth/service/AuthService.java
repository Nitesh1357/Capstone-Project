package com.project.Capstone.auth.service;
import com.project.Capstone.model.Role;
import com.project.Capstone.auth.dto.AuthResponse;
import com.project.Capstone.auth.dto.LoginRequest;
import com.project.Capstone.auth.dto.RegisterRequest;
import com.project.Capstone.auth.security.JwtService;
import com.project.Capstone.model.Role;
import com.project.Capstone.model.User;
import com.project.Capstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .isOAuthUser(false)
                .build();
        userRepository.save(user);
        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return new AuthResponse(jwtService.generateToken(user));
    }
}
