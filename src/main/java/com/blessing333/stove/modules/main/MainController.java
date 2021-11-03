package com.blessing333.stove.modules.main;

import com.blessing333.stove.modules.post.Post;
import com.blessing333.stove.modules.post.PostDTO;
import com.blessing333.stove.modules.post.PostRepository;
import com.blessing333.stove.modules.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private static final String HOME_URL = "/";
    private static final int POST_COUNT_PER_PAGE = 9;
    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping(HOME_URL)
    public String createMainView(HttpSession session, Model model,
                                 @PageableDefault(size = POST_COUNT_PER_PAGE, sort = "createdDate", direction = Sort.Direction.DESC)
                                    Pageable pageable){
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> posts = postPage.getContent();
        List<PostDTO> postDTOS = posts.stream().map(PostDTO::new).collect(Collectors.toList());
        model.addAttribute("postPage",postPage);
        model.addAttribute("posts",postDTOS);
        return "index";
    }
}
