package com.bharath.learning.blog.socialmediablogapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class ErrorDetails {

    private LocalDateTime timeStamp;
    private String message;
    private String details;

}