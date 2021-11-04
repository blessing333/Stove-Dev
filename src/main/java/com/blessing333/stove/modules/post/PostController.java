package com.blessing333.stove.modules.post;
import com.blessing333.stove.modules.category.Category;
import com.blessing333.stove.modules.category.CategoryRepository;
import com.blessing333.stove.modules.category.CategoryService;
import com.blessing333.stove.modules.comment.Comment;
import com.blessing333.stove.modules.comment.CommentForm;
import com.blessing333.stove.modules.comment.CommentService;
import com.blessing333.stove.modules.user.User;
import com.blessing333.stove.modules.user.UserConfig;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

import static com.blessing333.stove.infra.config.BlogConfig.POST_COUNT_PER_PAGE;
import static com.blessing333.stove.infra.config.UrlConfig.*;
/**
*
* 게시글 관련 요청을 처리하는 컨트롤러
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/01
**/

@Controller
@RequiredArgsConstructor
public class PostController {
    private static final String POST_VIEW_NAME = "post/post";
    private static final String POST_CREATE_FROM_VIEW_NAME = "post/post-form";
    private static final String POST_EDIT_VIEW_NAME = "post/post-edit";
    private static final String POST_LIST_VIEW_NAME = "post/posts";
    private final PostService postService;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final CommentService commentService;

    @GetMapping(POST_CREATE_FORM_URL)
    public String createPostFormView(HttpSession httpSession, Model model){
        PostForm postForm = new PostForm();
        User loginUser = (User) httpSession.getAttribute(UserConfig.USER_INFO_SESSION_ATTRIBUTE_NAME);
        postForm.setAuthor(loginUser.getNickname());
        postForm.setPublished(true);
        model.addAttribute(postForm);

        List<Category> categoryList =categoryRepository.findAll();
        model.addAttribute("categoryList",categoryList);
        return POST_CREATE_FROM_VIEW_NAME;
    }

    @GetMapping(POST_URL + "/{postId}")
    public String createPostView(@PathVariable Long postId,Model model){
        Post post = postService.loadPostInformationFromDB(postId);
        PostDTO postDTO = new PostDTO(post);
        model.addAttribute("post",postDTO);

        List<Comment> comments = commentService.loadCommentsByPost(post);
        model.addAttribute("comments",comments);

        CommentForm commentForm = new CommentForm();
        commentForm.setPost(post);
        model.addAttribute(commentForm);

        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList",categoryList);
        return POST_VIEW_NAME;
    }

    @PostMapping(POST_URL)
    public String addNewPost(@ModelAttribute PostForm postForm, HttpSession httpSession){
        Long addedPostId = postService.addNewPost(postForm);
        String postPath = "/"+addedPostId;
        return REDIRECT_URL + POST_URL + postPath;
    }

    @DeleteMapping(POST_URL+"/{postId}")
    public String deletePost(HttpSession httpSession, @PathVariable Long postId, RedirectAttributes redirectAttributes){
        postService.deletePost(postId);
        redirectAttributes.addFlashAttribute("삭제가 완료되었습니다");
        return REDIRECT_URL + HOME_URL;
    }

    @GetMapping(POST_EDIT_URL+"/{postId}")
    public String createPostEditView(@PathVariable Long postId, RedirectAttributes redirectAttributes,Model model){
        Post post = postService.loadPostInformationFromDB(postId);
        PostEditForm postEditForm = new PostEditForm(post);
        model.addAttribute(postEditForm);
        List<Category> categoryList =categoryRepository.findAll();
        model.addAttribute("categoryList",categoryList);
        return POST_EDIT_VIEW_NAME;
    }

    @PostMapping(POST_EDIT_URL)
    public String editPost(PostEditForm postEditForm,RedirectAttributes redirectAttributes){
        Long updatedPostId = postService.updatePost(postEditForm);
        String idPath = "/" + updatedPostId;
        return REDIRECT_URL + POST_URL+idPath;
    }

    @GetMapping("/post/category/{categoryId}")
    public String createPostViewByCategory(@PathVariable Long categoryId,
                                            @PageableDefault(size = POST_COUNT_PER_PAGE, sort = "createdDate",
                                                direction = Sort.Direction.DESC)Pageable pageable,Model model){
        Category category = categoryService.loadCategoryFromDB(categoryId);
        Page<Post> postPage = postRepository.findByCategory(category, pageable);
        List<Post> posts = postPage.getContent();
        List<PostDTO> postDTOS = posts.stream().map(PostDTO::new).collect(Collectors.toList());
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("postPage",postPage);
        model.addAttribute("posts",postDTOS);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("category",category);
        return POST_LIST_VIEW_NAME;
    }
}
