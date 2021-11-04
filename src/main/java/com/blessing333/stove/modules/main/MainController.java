package com.blessing333.stove.modules.main;

import com.blessing333.stove.modules.category.Category;
import com.blessing333.stove.modules.category.CategoryRepository;
import com.blessing333.stove.modules.post.Post;
import com.blessing333.stove.modules.post.PostDTO;
import com.blessing333.stove.modules.post.PostRepository;
import com.blessing333.stove.modules.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import static com.blessing333.stove.infra.config.BlogConfig.POST_COUNT_PER_PAGE;
import static com.blessing333.stove.infra.config.UrlConfig.HOME_URL;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final static String SEARCH_VIEW_NAME = "search";
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final PostService postService;

    @GetMapping(HOME_URL)
    public String createMainView(HttpSession session, Model model,
                                 @PageableDefault(size = POST_COUNT_PER_PAGE, sort = "createdDate", direction = Sort.Direction.DESC)
                                    Pageable pageable){
        Page<Post> postPage = postRepository.findByPublished(true,pageable);
        List<Post> posts = postPage.getContent();
        List<PostDTO> postDTOS = posts.stream().map(PostDTO::new).collect(Collectors.toList());
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("postPage",postPage);
        model.addAttribute("posts",postDTOS);
        model.addAttribute("categoryList",categoryList);
        return "index";
    }

    @GetMapping("/search")
    public String searchView(String keyword, @PageableDefault(size = POST_COUNT_PER_PAGE,
                            sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,Model model){
        Page<Post> postPage = postRepository.findByKeyword(keyword, pageable);
        List<Post> posts = postPage.getContent();
        List<PostDTO> postDTOS = posts.stream().map(PostDTO::new).collect(Collectors.toList());
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("keyword",keyword);
        model.addAttribute("postPage",postPage);
        model.addAttribute("posts",postDTOS);
        model.addAttribute("categoryList",categoryList);
        return SEARCH_VIEW_NAME;

    }

}
