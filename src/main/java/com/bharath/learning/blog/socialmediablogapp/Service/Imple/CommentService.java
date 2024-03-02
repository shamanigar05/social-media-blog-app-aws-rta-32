package com.bharath.learning.blog.socialmediablogapp.Service.Imple;

import com.bharath.learning.blog.socialmediablogapp.Dto.CommentDto;
import com.bharath.learning.blog.socialmediablogapp.Dto.PatchDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postid,CommentDto commentDto);
    List<CommentDto> getAllCommentByPostId(long postId);
    CommentDto getCommentByPostIdAndCommentId(long postId,long id);
    CommentDto updateCommentByPostIdAndCommentId(long postId,long id,CommentDto commentDto);

    CommentDto updateCommentPartaillyByPostIdAndCommentId(Long postId, Long id, PatchDto patchDto);
}
