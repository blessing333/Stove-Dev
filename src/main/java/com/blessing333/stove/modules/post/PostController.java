package com.blessing333.stove.modules.post;
import com.blessing333.stove.modules.user.User;
import com.blessing333.stove.modules.user.UserConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class PostController {
    private static final String POST_URL = "/post";
    private static final String POST_CREATE_FORM_URL = POST_URL + "/post-form";
    private static final String POST_VIEW_NAME = "post/post";
    private static final String POST_CREATE_FROM_VIEW_NAME = "post/post-form";
    private final PostService postService;

    @GetMapping(POST_CREATE_FORM_URL)
    public String createPostFormView(HttpSession httpSession, Model model){
        PostForm postForm = new PostForm();
        User loginUser = (User) httpSession.getAttribute(UserConfig.USER_INFO_SESSION_ATTRIBUTE_NAME);
        postForm.setAuthor(loginUser.getNickname());
        model.addAttribute(postForm);
        return POST_CREATE_FROM_VIEW_NAME;
    }

    @GetMapping(POST_URL + "/{postId}")
    public String createPostView(@PathVariable Long postId,Model model){
        Post post = postService.loadPostInformationFromDB(postId);
        model.addAttribute(post);
        return POST_VIEW_NAME;
    }

    @PostMapping(POST_URL)
    public String addNewPost(@ModelAttribute PostForm postForm, HttpSession httpSession){
        Long addedPostId = postService.addNewPost(postForm);
        return "redirect:/post/" + addedPostId;
    }
}
