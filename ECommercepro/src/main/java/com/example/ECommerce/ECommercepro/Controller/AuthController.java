package com.example.ECommerce.ECommercepro.Controller;

import com.example.ECommerce.ECommercepro.Model.Users;
import com.example.ECommerce.ECommercepro.Repository.UsersRepo;
import com.example.ECommerce.ECommercepro.Security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private UsersRepo userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;


    @PostMapping("/register")
    public String register(@RequestBody @Valid Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("ROLE_USER")); // Assign default role
        }

        userRepository.save(user);
        return "User registered successfully";
    }


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = jwtUtil.generateToken(email);

        return Map.of("token", token);
    }
}

