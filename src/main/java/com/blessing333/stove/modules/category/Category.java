package com.blessing333.stove.modules.category;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
/**
*
* 게시판의 카테고리를 표현한 jpa entity
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/04
**/
@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id @GeneratedValue @Column(name = "CATEGORY_ID")
    private Long id;

    @Column(unique = true)
    private String categoryName;

    private LocalDateTime createdDate;

    public Category(String categoryName) {
        this.setCategoryName(categoryName);
        setCreatedDate(LocalDateTime.now());
    }
}
