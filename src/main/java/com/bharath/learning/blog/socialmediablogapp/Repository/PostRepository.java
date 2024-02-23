package com.bharath.learning.blog.socialmediablogapp.Repository;

import com.bharath.learning.blog.socialmediablogapp.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
