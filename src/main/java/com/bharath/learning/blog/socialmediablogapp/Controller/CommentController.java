package com.bharath.learning.blog.socialmediablogapp.Controller;


import com.bharath.learning.blog.socialmediablogapp.Dto.CommentDto;
import com.bharath.learning.blog.socialmediablogapp.Dto.PatchDto;
import com.bharath.learning.blog.socialmediablogapp.Service.Imple.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Create new Post - /api/v1/posts/{postId}/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto) {
        CommentDto savedCommentDto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
    }

    // Get All Comments - /api/v1/posts/{postId}/comments
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> fetchAllCommentsByPostId(@PathVariable("postId") Long postId) {
        List<CommentDto> commentDtoList = commentService.getAllCommentByPostId(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);

    }

    // Get Comments By CommentId & postId - /api/v1/posts/{postId}/comments{id}

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> fetchcommentByPostIdAndCommentId(@PathVariable("postId") Long postId, @PathVariable("id") Long id) {
        CommentDto commentDto = commentService.getCommentByPostIdAndCommentId(postId, id);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);

    }

    // Put Update Comment By PostId and CommentId /api/v1/posts/{postId}/comments/{id}

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentByPostIdAndCommentId(@PathVariable("postId") Long postId,
                                                                        @PathVariable("id") Long id,
                                                                        @RequestBody CommentDto commentDto) {
        CommentDto updateCommentDto = commentService.updateCommentByPostIdAndCommentId(postId, id, commentDto);
        return new ResponseEntity<>(updateCommentDto, HttpStatus.OK);

    }

    // // Delete Comment By PostId and CommentId /api/v1/posts/{postId}/comments/{id}

    //// Patch Comment By PostId and CommentId /api/posts/{postId}/comments/{id}


    @PatchMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> PartiallyUpdateCommentByPostIdAndCommentId(@PathVariable("postId") Long postId,
                                                                                 @PathVariable("id") Long id,
                                                                                 @RequestBody PatchDto patchDto) {
        CommentDto updateCommentDto = null;
        if (patchDto.getOperation().equalsIgnoreCase("updated")) {
            updateCommentDto = commentService.updateCommentPartaillyByPostIdAndCommentId(postId, id, patchDto);
        } else if (patchDto.getOperation().equalsIgnoreCase("detele")){
    }

            return new ResponseEntity<>(updateCommentDto, HttpStatus.OK);

    }

}
