package com.blessing333.stove.modules.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
/**
*
* 카테고리 생성에 필요한 데이터를 담는 Form 객체
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/05
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {
    @NotBlank
    String categoryName;
}
