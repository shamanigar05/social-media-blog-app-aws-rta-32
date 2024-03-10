package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.service.impl;

import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.PostDto;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.entity.Post;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.exception.ResourceNotFoundException;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.payload.PostResponse;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.repository.PostRepository;
import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        //Map PostDTO to Post Entity
        Post post = mapDtoToEntity(postDto);

        //save to DB
        Post savedPost = postRepository.save(post);

        //Map Post Entity to PostDTO
        PostDto savedPostDto = mapEntityToDto(savedPost);
        return savedPostDto;
    }


    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Pageable pageable;
        if(sortBy != null && sortDir != null) {

            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
            pageable = PageRequest.of(pageNo, pageSize, sort);
        } else {
            pageable = PageRequest.of(pageNo, pageSize);
        }


        //List<Post> allPosts = postRepository.findAll();

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();

        //Map Post Entity to PostDto
        //List<PostDto> postDtoList = allPosts.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());
        List<PostDto> postDtoList = postList.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());

        //Customize the Post Resource Response
        PostResponse postResponse = PostResponse
                .builder()
                .content(postDtoList)
                .pageNo(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .isLastPage(posts.isLast())
                .build();



        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        PostDto postDto = mapEntityToDto(post);
        return postDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));

        existingPost.setDescription(postDto.getDescription());
        existingPost.setContent(postDto.getContent());
        existingPost.setTitle(postDto.getTitle());

        Post updatedPost = postRepository.save(existingPost);
        return mapEntityToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        postRepository.delete(existingPost);
    }


    private PostDto mapEntityToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapDtoToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}
