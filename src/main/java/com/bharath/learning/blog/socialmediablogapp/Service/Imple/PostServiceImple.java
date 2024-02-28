package com.bharath.learning.blog.socialmediablogapp.Service.Imple;

import com.bharath.learning.blog.socialmediablogapp.Dto.PostDto;
import com.bharath.learning.blog.socialmediablogapp.Entity.Post;
import com.bharath.learning.blog.socialmediablogapp.Exception.ResourceNotFoundException;
import com.bharath.learning.blog.socialmediablogapp.Payload.PostResponse;
import com.bharath.learning.blog.socialmediablogapp.Repository.PostRepository;
import com.bharath.learning.blog.socialmediablogapp.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImple implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public PostDto createPost(PostDto postDto) {
        //Map post DTO to PostEntity
        Post post = mapDtoToEntity(postDto);


        //Save to DB
        Post savePost = postRepository.save(post);

        //Map PostEntity to Post DTO
        PostDto savedPostDto = mapEntityToDto(savePost);
        return savedPostDto;
    }


    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {


    Pageable pageable;
        if(sortBy != null && sortDir !=null){
           Sort sort =  sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                    :Sort.by(sortBy).descending();
         pageable = PageRequest.of(pageNo, pageSize, sort);
    }else{
        pageable = PageRequest.of(pageNo, pageSize);
        }


        //Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        //List<Post> allPosts = postRepository.findAll();
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();

        //Map Post Entity to PostDto
       //List<PostDto>postDtoList = allPosts.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());
        List<PostDto> postDtoList = postList.stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());

        //Customize the Post Source Resourse
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
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",String.valueOf(id)));
        PostDto postDto = mapEntityToDto(post);
        return postDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",String.valueOf(id)));

        existingPost.setDescription(postDto.getDescription());
        existingPost.setContent(postDto.getContent());
        existingPost.setTitle(postDto.getTitle());
        Post updatePost = postRepository.save(existingPost);
        return mapEntityToDto(updatePost);
    }

    @Override
    public PostDto deletePost(long id) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",String.valueOf(id)));
        if(existingPost.getId() >= 0){
            postRepository.deleteById(id);
        }
        existingPost.setTitle(null);
        existingPost.setDescription("Record is successfully delete with id "+ id);
        existingPost.setContent(null);
        return mapEntityToDto(existingPost);
    }

    private PostDto mapEntityToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

    private Post mapDtoToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(post.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}
