package com.blessing333.stove.modules.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreateFactory {
    @Autowired CategoryService categoryService;
    public Long createCategory(){
        CategoryForm categoryForm = new CategoryForm("test-category");
        return categoryService.addNewCategory(categoryForm);
    }
}
