package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.controller;


import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.JWTAuthResponse;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.LoginDto;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.RegisterDto;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //HTTP POST - "/login", "/signin"

    @PostMapping(value = { "/login", "/signin" })

    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    //HTTP POST - "/register", "/signup“
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    }


