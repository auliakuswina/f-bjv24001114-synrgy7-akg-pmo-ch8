package com.example.batch7.ch8.dto.req;

import lombok.Data;


@Data
public class ResetPasswordModel {
    public String email;

    public String otp;
    public String newPassword;
}