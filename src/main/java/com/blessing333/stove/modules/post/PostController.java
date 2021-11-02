package com.blessing333.stove.modules.post;
import com.blessing333.stove.infra.config.UrlConfig;
import com.blessing333.stove.modules.comment.Comment;
import com.blessing333.stove.modules.comment.CommentForm;
import com.blessing333.stove.modules.comment.CommentService;
import com.blessing333.stove.modules.user.User;
import com.blessing333.stove.modules.user.UserConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.util.List;

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
    private final PostService postService;
    private final CommentService commentService;

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
        List<Comment> comments = commentService.loadCommentsByPost(post);
        CommentForm commentForm = new CommentForm();
        commentForm.setPost(post);

        model.addAttribute("comments",comments);
        model.addAttribute(commentForm);
        model.addAttribute(post);
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
        PostEditForm postEditForm = new PostEditForm();
        postEditForm.setId(post.getId());
        postEditForm.setTitle(post.getTitle());
        postEditForm.setAuthor(post.getAuthor());
        postEditForm.setContent(post.getContent());
        postEditForm.setPrivatePost(post.isPrivatePost());
        model.addAttribute(postEditForm);
        return POST_EDIT_VIEW_NAME;
    }

    @PostMapping(POST_EDIT_URL)
    public String editPost(PostEditForm postEditForm,RedirectAttributes redirectAttributes){
        Long updatedPostId = postService.updatePost(postEditForm);
        String idPath = "/" + updatedPostId;
        redirectAttributes.addFlashAttribute("message","게시글 수정이 완료되었습니다");
        return REDIRECT_URL + POST_URL+idPath;
    }
}
