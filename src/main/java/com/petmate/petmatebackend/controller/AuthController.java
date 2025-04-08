package com.petmate.petmatebackend.controller;

import com.petmate.petmatebackend.dto.AuthResponse;
import com.petmate.petmatebackend.dto.UserLoginRequest;
import com.petmate.petmatebackend.dto.UserSignupRequest;
import com.petmate.petmatebackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String register(@RequestBody UserSignupRequest request){
        authService.register(request);
        return "User registeres successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserLoginRequest request){
        return authService.login(request);
    }
}
