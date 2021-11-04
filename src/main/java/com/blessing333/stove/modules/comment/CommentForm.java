package com.blessing333.stove.modules.comment;

import com.blessing333.stove.modules.post.Post;
import lombok.Data;
import javax.validation.constraints.NotBlank;
/**
*
* 댓글 생성에 필요한 값을 담는 Form 객체
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/05
**/
@Data
public class CommentForm {
    @NotBlank
    private Post post;
    @NotBlank
    private String writer;
    @NotBlank
    private String content;
}
