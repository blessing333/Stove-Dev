package com.blessing333.stove.modules.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostForm {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String content;
    private boolean privatePost;
}
