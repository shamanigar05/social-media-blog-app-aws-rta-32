package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String usernameOrEmail;
    private String password;
}
