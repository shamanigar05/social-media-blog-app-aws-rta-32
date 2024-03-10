package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RegisterDto {

    private String name;
    private String username;
    private String email;
    private String password;

}
