package com.blessing333.stove.modules.post;

import com.blessing333.stove.modules.category.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
*
* 게시글 수정을 위한 DTO
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/01
**/
@Data
@NoArgsConstructor
public class PostEditForm {
    @NotBlank
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String content;
    @NotBlank
    private Long category;
    @NotBlank
    private boolean published;
    private String thumbnail;

    PostEditForm(Post post){
        setId(post.getId());
        setTitle(post.getTitle());
        setContent(post.getContent());
        setCategory(post.getCategory().getId());
        setAuthor(post.getAuthor());
        setPublished(post.isPublished());
        setThumbnail(post.getThumbnail());
    }
}
