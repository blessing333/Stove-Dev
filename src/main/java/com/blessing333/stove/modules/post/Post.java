package com.blessing333.stove.modules.post;
import com.blessing333.stove.modules.category.Category;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
*
* 블로그의 게시글을 표현하는 JPA Entity Class
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/10/29
**/
@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")

public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    private Category category;

    private String author;

    private LocalDateTime createdDate;

    private boolean published;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String thumbnail;

    public static Post createNewPost(String title,String content,String author,boolean published,Category category,String thumbnail){
        Post instance = new Post();
        instance.setTitle(title);
        instance.setContent(content);
        instance.setAuthor(author);
        instance.setCreatedDate(LocalDateTime.now());
        instance.setPublished(published);
        instance.setCategory(category);
        instance.setThumbnail(thumbnail);
        return instance;
    }

    public void editPostInformation(String title,String content,String author,boolean published, Category category, String thumbnail){
        setTitle(title);
        setContent(content);
        setAuthor(author);
        setCreatedDate(LocalDateTime.now());
        setPublished(published);
        setCategory(category);
        setThumbnail(thumbnail);
    }
}
