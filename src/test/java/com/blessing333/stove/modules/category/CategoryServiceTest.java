package com.blessing333.stove.modules.category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CategoryServiceTest {
    @Autowired CategoryService categoryService;
    @Autowired CategoryRepository categoryRepository;

    @DisplayName("카테고리 추가")
    @Test
    public void addNewCategoryTest() {
        //given
        CategoryForm categoryForm = new CategoryForm("test-category");
        Long id = categoryService.addNewCategory(categoryForm);
        //when
        Category category = categoryService.loadCategoryFromDB(id);
        //then
        assertNotNull(category);
        assertThat(category.getCategoryName()).isEqualTo(categoryForm.getCategoryName());
    }

    @DisplayName("카테고리 삭제")
    @Test
    public void deleteCategoryTest() {
        //given
        CategoryForm categoryForm = new CategoryForm("test-category");
        Long id = categoryService.addNewCategory(categoryForm);
        //when
        categoryService.deleteCategory(id);
        //then
        assertFalse(categoryRepository.existsById(id));
    }
}