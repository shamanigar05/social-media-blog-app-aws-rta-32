package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.controller;

import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.PostDto;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.payload.PostResponse;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/posts")
@Tag(
        name = "SOCIAL MEDIA POST RESOURCE CRUD REST APIS"
)
public class PostController {

    @Autowired
    private PostService postService;

    //POST /api/posts


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Create Post Rest API",
            description = "Create Rest API is used to create new Posts on Social Media Blog Application"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto) {
        PostDto savedPostDto = postService.createPost(postDto);
        return new ResponseEntity(savedPostDto, HttpStatus.CREATED);
    }

    //GET /api/posts
    // Pagination and Sorting
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Get All Post Rest API",
            description = "Get All Post Rest API is used to fetch all the posts from all the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    public PostResponse getAllPosts(
        @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "id", required = false) String sortDir

    ) {
       return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //GET /api/posts/{id}
    @GetMapping("/{id}")
    @Operation(
            summary = "Get All Post By Id Rest API",
            description = "Get All Post By Id Rest API is used to fetch all the single posts from all the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    public ResponseEntity<PostDto> getPostById(@PathVariable long id) {
       return ResponseEntity.ok(postService.getPostById(id));
    }

    //PUT /api/posts/{id}

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Update Post Rest API",
            description = "Update Post Rest API is used update a particular posts in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    public ResponseEntity<PostDto> updatePost(@RequestBody @Valid PostDto postDto, @PathVariable long id) {
       PostDto updatedPostResponse = postService.updatePost(postDto, id);
       return ResponseEntity.ok(updatedPostResponse);
    }

    //DELETE ///api/posts/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Delete Post Rest API",
            description = "Delete Post Rest API is used delete a particular posts in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Deleted Successfully Post Resource :: "+id);
    }


}
