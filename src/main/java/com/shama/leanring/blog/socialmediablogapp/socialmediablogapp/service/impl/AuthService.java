package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.service.impl;

import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.LoginDto;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.RegisterDto;

public interface AuthService {

    //login
    String login(LoginDto loginDto);

    //register
    String register(RegisterDto registerDto);
}
