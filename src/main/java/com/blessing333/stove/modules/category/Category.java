package com.blessing333.stove.modules.category;

import com.blessing333.stove.modules.post.Post;

import javax.persistence.*;

@Entity
public class Category {
    @Id @GeneratedValue @Column(name = "CATEGORY_ID")
    private Long id;
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;
}
