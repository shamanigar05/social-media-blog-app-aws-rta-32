package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.service;

import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.PostDto;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
