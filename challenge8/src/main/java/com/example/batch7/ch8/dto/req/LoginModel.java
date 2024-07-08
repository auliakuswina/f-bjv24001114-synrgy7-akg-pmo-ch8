package com.example.batch7.ch8.dto.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginModel {
    @NotEmpty(message = "Username or email is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
}
