package com.blessing333.stove.modules.post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
*
* 블로그의 게시글을 표현하는 JPA Entity
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/10/29
**/
@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String content;

    private String author;

    private LocalDateTime createdDate;

    private boolean published;

    private boolean deleted;

    public static Post createNewPost(String title,String content,String author,boolean published){
        Post instance = new Post();
        instance.setTitle(title);
        instance.setContent(content);
        instance.setAuthor(author);
        instance.setCreatedDate(LocalDateTime.now());
        instance.setPublished(published);
        instance.setDeleted(false);
        return instance;
    }
}
