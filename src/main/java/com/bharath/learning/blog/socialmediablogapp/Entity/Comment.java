package com.bharath.learning.blog.socialmediablogapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //name
    private String name;
    //Email
    private String email;
    //body
    private String body;

    //Mapping b/w Comments to Post
    //Comment table is managing relatioship b/w posts and comments table

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
}
