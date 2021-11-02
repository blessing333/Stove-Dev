package com.blessing333.stove.modules.comment;

import com.blessing333.stove.modules.post.Post;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class CommentForm {
    @NotBlank
    private Post post;
    @NotBlank
    private String writer;
    @NotBlank
    private String content;
}
