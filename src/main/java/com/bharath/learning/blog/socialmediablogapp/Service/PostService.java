package com.bharath.learning.blog.socialmediablogapp.Service;

import com.bharath.learning.blog.socialmediablogapp.Dto.PostDto;

import java.util.List;

public interface PostService {

   PostDto createPost(PostDto postDto);

   List<PostDto>getAllPosts();

   PostDto getPostById(Long id);


   PostDto updatePost(PostDto postDto, long id);

   PostDto deletePost(long id);
}
