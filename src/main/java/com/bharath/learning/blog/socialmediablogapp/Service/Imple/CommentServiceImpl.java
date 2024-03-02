package com.bharath.learning.blog.socialmediablogapp.Service.Imple;

import com.bharath.learning.blog.socialmediablogapp.Dto.CommentDto;
import com.bharath.learning.blog.socialmediablogapp.Dto.PatchDto;
import com.bharath.learning.blog.socialmediablogapp.Entity.Comment;
import com.bharath.learning.blog.socialmediablogapp.Entity.Post;
import com.bharath.learning.blog.socialmediablogapp.Exception.ResourceNotFoundException;
import com.bharath.learning.blog.socialmediablogapp.Repository.CommentRepository;
import com.bharath.learning.blog.socialmediablogapp.Repository.PostRepository;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


    @Override
    public CommentDto createComment(long postid, CommentDto commentDto) {

        //Map Comment DTO to Comment Entity
        Comment comment = mapDtoToEntity(commentDto);

        //Fetch Post from post Repository using PostId from Request URI
       Post post = postRepository.findById(postid).orElseThrow(()->  new ResourceNotFoundException("Post","Id",String.valueOf(postid)));

        //Set Comment to Post
        comment.setPost(post);

        //Save Comment to DB
        Comment savedCommentEntity = commentRepository.save(comment);

        //Map Comment Entity to Comment DTO
        CommentDto saveCommentDto = mapEntityToDto(savedCommentEntity);
        return saveCommentDto;
    }

    @Override
    public List<CommentDto> getAllCommentByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        //Map Entity to Dto
        List<CommentDto>commentDtos = comments.stream().map(comment -> mapEntityToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long id) {

        // Fetch Post By PostId
        Post  postEntity = postRepository.findById(postId).orElseThrow(() ->  new ResourceNotFoundException("Post","id",String.valueOf(postId)));

        // Fetch Comment By CommentId
        Comment  commentEntity = commentRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Comment","id",String.valueOf(id)));

        // validate comment belong to that Particular Post
        if(!commentEntity.getPost().getId().equals(postEntity.getId())){
            throw  new RuntimeException("Bad Request Comment Not Found");
        }
        // Map Comment Entity to Comment DTO
        CommentDto commentDto = mapEntityToDto(commentEntity);
        return commentDto;
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long id, CommentDto commentDto) {

        // Fetch Post By PostId
        Post  postEntity = postRepository.findById(postId).orElseThrow(() ->  new ResourceNotFoundException("Post","id",String.valueOf(postId)));

        // Fetch Comment By CommentId
        Comment  commentEntity = commentRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Comment","id",String.valueOf(id)));

        // validate comment belong to that Particular Post
        if(!commentEntity.getPost().getId().equals(postEntity.getId())){
            throw  new RuntimeException("Bad Request Comment Not Found");
        }
        // Update old Comment Details With new Comment Dto
        commentEntity.setName(commentDto.getName());
        commentEntity.setEmail(commentDto.getEmail());
        commentEntity.setBody(commentDto.getBody());

        // Saved Update Commit Entity
        Comment updateCommentEntity = commentRepository.save(commentEntity);

        // Map Comment Entity to Comment DTO
        CommentDto updateCommentDto = mapEntityToDto(updateCommentEntity);
        return updateCommentDto;

    }

    @Override
    public CommentDto updateCommentPartaillyByPostIdAndCommentId(Long postId, Long id, PatchDto patchDto) {

        // Fetch Post By PostId
        Post  postEntity = postRepository.findById(postId).orElseThrow(() ->  new ResourceNotFoundException("Post","id",String.valueOf(postId)));

        // Fetch Comment By CommentId
        Comment  commentEntity = commentRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Comment","id",String.valueOf(id)));

        // validate comment belong to that Particular Post
        if(!commentEntity.getPost().getId().equals(postEntity.getId())) {
            throw new RuntimeException("Bad Request Comment Not Found");
        }


        partaillyUpdateCommentEntity(patchDto, commentEntity);

        // Saved Patch Commit Entity
        Comment updateCommentEntity = commentRepository.save(commentEntity);

        // Map Comment Entity to Comment DTO
        CommentDto updateCommentDto = mapEntityToDto(updateCommentEntity);
        return updateCommentDto;

    }



    private CommentDto mapEntityToDto(Comment savedCommentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(savedCommentEntity.getId());
        commentDto.setName(savedCommentEntity.getName());
        commentDto.setBody(savedCommentEntity.getBody());
        commentDto.setEmail(savedCommentEntity.getEmail());
        return commentDto;
    }

    private Comment mapDtoToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

    private void partaillyUpdateCommentEntity(PatchDto patchDto, Comment commentEntity) {
        switch (patchDto.getKey()){
            case "Email"  :
                commentEntity.setEmail(patchDto.getValue());
                break;
            case "Body" :
                commentEntity.setBody(patchDto.getValue());
                break;
            case "Name" :
                commentEntity.setName(patchDto.getValue());
        }
    }
}
