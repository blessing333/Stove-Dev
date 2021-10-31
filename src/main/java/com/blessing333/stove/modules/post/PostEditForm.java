package com.blessing333.stove.modules.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
*
* 게시글 수정을 위한 DTO
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/01
**/
@Data
public class PostEditForm {
    @NotBlank
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String content;
    private boolean privatePost;
}
