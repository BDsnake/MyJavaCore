package com.example.dto;

import lombok.Data;

import javax.validation.constraints.Email;

/**
 * @author BDsnake
 * @since 2023/4/18 9:39
 */
@Data
public class RegisterDto {
    String username;
    String password;
    @Email
    String email;
    String code;
}
