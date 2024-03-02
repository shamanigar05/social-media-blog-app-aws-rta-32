package com.bharath.learning.blog.socialmediablogapp.Exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BlogAPIException  extends  RuntimeException {

    private HttpStatus status;
    private String message;
}