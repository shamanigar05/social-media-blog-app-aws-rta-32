package com.bharath.learning.blog.socialmediablogapp.Service;

import com.bharath.learning.blog.socialmediablogapp.Dto.PostDto;
import com.bharath.learning.blog.socialmediablogapp.Payload.PostResponse;

import java.util.List;

public interface PostService {

   PostDto createPost(PostDto postDto);

   PostResponse getAllPosts(int pageNo, int pageSize);

   PostDto getPostById(Long id);


   PostDto updatePost(PostDto postDto, long id);

   PostDto deletePost(long id);
}
