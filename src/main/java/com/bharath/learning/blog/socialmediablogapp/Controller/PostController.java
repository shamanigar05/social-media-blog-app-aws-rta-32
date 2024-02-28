package com.bharath.learning.blog.socialmediablogapp.Controller;

import com.bharath.learning.blog.socialmediablogapp.Dto.PostDto;
import com.bharath.learning.blog.socialmediablogapp.Payload.PostResponse;
import com.bharath.learning.blog.socialmediablogapp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // /api/posts

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

        PostDto savePostDto = postService.createPost(postDto);
        return new ResponseEntity(savePostDto, HttpStatus.CREATED);
    }


    // Get/api/posts
    // Pagination and Sorting
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "id",required = false)String sortDir

    ) {
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);

    }

    // GET/api/posts/{id}

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id) {

        return ResponseEntity.ok(postService.getPostById(id));
    }

    // PUT/api/posts/{id}

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id) {
        PostDto updatePostResponse = postService.updatePost(postDto, id);
       return ResponseEntity.ok(updatePostResponse);

    }

    // DELETE/api/posts/{id}

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable long id) {
        PostDto updatePostResponse = postService.deletePost(id);
        return ResponseEntity.ok(updatePostResponse);

    }

}
