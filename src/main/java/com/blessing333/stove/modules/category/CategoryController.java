package com.blessing333.stove.modules.category;

import com.blessing333.stove.infra.config.UrlConfig;
import com.blessing333.stove.modules.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static com.blessing333.stove.infra.config.UrlConfig.*;

/**
*
* 카테고리 관련 요청을 처리해주는 컨트롤러
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/05
**/
@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final static String CATEGORY_DELETE_FAIL_MESSAGE = "해당 카테고리에 게시물이 존재하면 삭제할 수 없습니다.";
    private final static String CATEGORY_CREATE_FAIL_MESSAGE = "같은 이름의 카테고리가 이미 존재합니다.";
    private final static String CATEGORY_DELETE_SUCCESS_MESSAGE = "카테고리 삭제가 완료되었습니다.";
    private final static String CATEGORY_CREATE_SUCCESS_MESSAGE = "카테고리 생성이 완료되었습니다.";
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    /*
    * 새로운 카테고리를 생성하는 메소드.
    * 만약 같은 이름의 카테고리가 이미 존재한다면 카테고리를 추가하지 않고 실패 메세지를 전달한다.
    * */
    @PostMapping(CATEGORY_URL)
    public String createNewCategory(CategoryForm categoryForm, RedirectAttributes redirectAttributes){
        if(categoryRepository.existsByCategoryName(categoryForm.categoryName)){
            redirectAttributes.addFlashAttribute("message",CATEGORY_CREATE_FAIL_MESSAGE);
        }
        else{
            categoryService.addNewCategory(categoryForm);
            redirectAttributes.addFlashAttribute("message",CATEGORY_CREATE_SUCCESS_MESSAGE);
        }
        return REDIRECT_URL + ADMIN_CATEGORY_SETTING_URL;
    }

    /*
     * 카테고리를 삭제하는 메소드.
     * 만약 같은 해당 카테고리에 게시글이 존재한다면, 카테고리를 삭제하지 않고 실패 메세지를 전달한다.
     * */
    @DeleteMapping(CATEGORY_URL +"/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId, RedirectAttributes redirectAttributes){
        Category category = categoryService.loadCategoryFromDB(categoryId);
        if (postRepository.existsByCategory(category)){
            redirectAttributes.addFlashAttribute("message",CATEGORY_DELETE_FAIL_MESSAGE);
        }
        else{
            categoryService.deleteCategory(categoryId);
            redirectAttributes.addFlashAttribute("message",CATEGORY_DELETE_SUCCESS_MESSAGE);
        }
        return REDIRECT_URL + ADMIN_CATEGORY_SETTING_URL;
    }

}
