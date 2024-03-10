package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodingDecodingUtility {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("social:: "+passwordEncoder.encode("social"));
        System.out.println("Admin:: "+passwordEncoder.encode("admin"));

    }
}
