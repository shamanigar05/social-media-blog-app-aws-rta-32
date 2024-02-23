package com.bharath.learning.blog.socialmediablogapp.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="content")
    private String content;
}
