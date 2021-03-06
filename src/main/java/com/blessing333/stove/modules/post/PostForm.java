package com.blessing333.stove.modules.post;

import com.blessing333.stove.modules.category.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/**
*
* 게시글 생성을 위한 DTO
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/01
**/
@Data
public class PostForm {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String content;
    @NotBlank
    private boolean published;
    @NotBlank
    private Long category;
    private String thumbnail;
}
