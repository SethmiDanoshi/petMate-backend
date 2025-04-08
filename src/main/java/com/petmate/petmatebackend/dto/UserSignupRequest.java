package com.petmate.petmatebackend.dto;

import com.petmate.petmatebackend.model.User;
import lombok.Data;

@Data
public class UserSignupRequest {
    private String email;
    private String password;
    private String fullName;
    private User.Role role;
}
