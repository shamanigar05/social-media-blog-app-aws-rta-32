package com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.payload;

import com.shama.leanring.blog.socialmediablogapp.socialmediablogapp.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {


    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;


}
