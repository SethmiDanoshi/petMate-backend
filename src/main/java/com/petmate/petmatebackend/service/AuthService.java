package com.petmate.petmatebackend.service;

import com.petmate.petmatebackend.dto.AuthResponse;
import com.petmate.petmatebackend.dto.UserLoginRequest;
import com.petmate.petmatebackend.dto.UserSignupRequest;
import com.petmate.petmatebackend.model.User;
import com.petmate.petmatebackend.repository.UserRepository;
import com.petmate.petmatebackend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(UserSignupRequest request){
        if (userRepo.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already taken.");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(request.getRole())
                .build();

        userRepo.save(user);
    }

    public AuthResponse login(UserLoginRequest request){
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid credentials"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials.");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }



}
