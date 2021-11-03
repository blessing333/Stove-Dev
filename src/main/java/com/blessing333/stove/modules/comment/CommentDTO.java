package com.blessing333.stove.modules.comment;

import com.blessing333.stove.modules.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String writer;
    private Post post;
    private String content;
    private LocalDateTime createdDate;
}
