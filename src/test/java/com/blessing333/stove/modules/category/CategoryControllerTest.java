package com.blessing333.stove.modules.category;

import com.blessing333.stove.infra.config.UrlConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.blessing333.stove.infra.config.UrlConfig.*;
import static com.blessing333.stove.infra.config.UrlConfig.ADMIN_CATEGORY_SETTING_URL;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class CategoryControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired CategoryRepository categoryRepository;
    @Autowired CategoryService categoryService;

    @DisplayName("새로운 카테고리 생성 - 정상처리")
    @Test
    public void createNewCategoryTest() throws Exception {
        //given
        CategoryForm categoryForm = new CategoryForm("test-category");
        //when
        mockMvc.perform(post(CATEGORY_URL).param("categoryName",categoryForm.getCategoryName())
        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(ADMIN_CATEGORY_SETTING_URL));
        //then
        assertTrue(categoryRepository.existsByCategoryName(categoryForm.getCategoryName()));
    }

    @DisplayName("카테고리 삭제 - 정상처리")
    @Test
    public void deleteCategoryTest() throws Exception{
        //given
        CategoryForm categoryForm = new CategoryForm("test-category");
        Long id = categoryService.addNewCategory(categoryForm);
        //when
        mockMvc.perform(delete(CATEGORY_URL+"/"+id).param("categoryName",categoryForm.getCategoryName())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(ADMIN_CATEGORY_SETTING_URL));
        //then
        assertFalse(categoryRepository.existsByCategoryName(categoryForm.getCategoryName()));

    }
}