package com.bharath.learning.blog.socialmediablogapp.Service.Imple;

import com.bharath.learning.blog.socialmediablogapp.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postid,CommentDto commentDto);
    List<CommentDto> getAllCommentByPostId(long postId);
}
