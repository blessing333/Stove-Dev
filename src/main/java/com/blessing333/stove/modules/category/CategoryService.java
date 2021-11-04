package com.blessing333.stove.modules.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
/**
*
* 카테고리 생성, 삭제 등, 카테고리 관련 서비스를 제공하는 클래스
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/05
**/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
    private static final String CATEGORY_NOT_EXIST_MESSAGE = "카테고리가 존재하지 않습니다" ;
    private final CategoryRepository categoryRepository;
    /*
    * 애플리케이션 실행 시, 기본 카테고리를 생성해주는 메소드
    * */
    @PostConstruct
    public void initCategory(){
        Category category = new Category("BackEnd Development");
        Category category2 = new Category("FrontEnd Development");
        Category category3 = new Category("Android Development");
        categoryRepository.save(category);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
    }

    /*
     * 데이터베이스에서 카테고리 id를 통하여 카테고리 정보를 불러오는 메소드.
     * 데이터베이스에 id에 해당하는 카테고리가 존재하지 않을 경우 IllegalArgumentException을 발생시킨다.
     * 발생된 Exception은 ExceptionAdvice 클래스에서 처리한다.
     * */
    public Category loadCategoryFromDB(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException(CATEGORY_NOT_EXIST_MESSAGE));
    }

    @Transactional
    public Long addNewCategory(CategoryForm categoryForm) {
        Category newCategory = new Category(categoryForm.categoryName);
        categoryRepository.save(newCategory);
        return newCategory.getId();
    }

    @Transactional
    public Long deleteCategory(Long categoryId) {
        Category category =  loadCategoryFromDB(categoryId);
        categoryRepository.delete(category);
        return categoryId;
    }
}
