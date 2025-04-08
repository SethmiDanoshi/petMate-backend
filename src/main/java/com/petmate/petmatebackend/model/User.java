package com.petmate.petmatebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "users")

public class User {
    @Id
    private String id;

    private String email;
    private String password;
    private String fullName;

    private Role role;

    public enum Role{
        SELLER, CUSTOMER
    }
}
