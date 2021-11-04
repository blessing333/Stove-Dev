package com.blessing333.stove.modules.admin;

import com.blessing333.stove.modules.category.Category;
import com.blessing333.stove.modules.category.CategoryForm;
import com.blessing333.stove.modules.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.blessing333.stove.infra.config.UrlConfig.*;
/**
*
* 관리자 페이지에 대한 요청을 처리하는 컨트롤러
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/05
**/
@Controller
@RequiredArgsConstructor
public class AdminController {
    private static final String ADMIN_CATEGORY_SETTING_VIEW_NAME = "admin/category-setting";
    private final CategoryRepository categoryRepository;

    /*
    * 게시글 카테고리 설정 페이지를 생성해주는 메소드.
    * 모든 카테고리 정보와 카테고리 생성을 위한 카테고리 폼을 Model 객체에 담아 카테고리 설정 페이지를 생성한다.
    * */
    @GetMapping(ADMIN_CATEGORY_SETTING_URL)
    public String createCategorySettingPage(Model model){
        List<Category> categoryList =  categoryRepository.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("categoryForm",new CategoryForm());
        return ADMIN_CATEGORY_SETTING_VIEW_NAME;
    }
}
