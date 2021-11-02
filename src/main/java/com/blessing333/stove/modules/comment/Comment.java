package com.blessing333.stove.modules.comment;

import com.blessing333.stove.modules.post.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue
    private Long id;
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Lob
    private String content;
    private LocalDateTime createdDate;

    static public Comment createComment(CommentForm commentForm){
        Comment instance = new Comment();
        instance.content = commentForm.getContent();
        instance.post = commentForm.getPost();
        instance.writer = commentForm.getWriter();
        instance.setCreatedDate(LocalDateTime.now());
        return instance;

    }


}
