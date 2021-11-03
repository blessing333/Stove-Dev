package com.blessing333.stove.modules.post;

import com.blessing333.stove.modules.category.Category;

import javax.persistence.*;

@Entity
public class PostCategory {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
