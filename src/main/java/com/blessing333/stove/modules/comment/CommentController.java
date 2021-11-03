package com.blessing333.stove.modules.comment;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.blessing333.stove.infra.config.UrlConfig.*;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private static final String COMMENT_DELETE_SUCCESS_MESSAGE = "댓글이 삭제되었습니다.";

    private final CommentService commentService;

    @PostMapping(COMMENT_URL)
    public String addComment(CommentForm commentForm){
        commentService.addNewComment(commentForm);
        String postPath = "/" + commentForm.getPost().getId();
        return REDIRECT_URL + POST_URL + postPath;
    }

    @DeleteMapping(COMMENT_URL +"/{commentId}")
    public String deleteComment(@PathVariable Long commentId, RedirectAttributes redirectAttributes){
        Comment comment = commentService.loadCommentInformationFromDB(commentId);
        Long postId = comment.getPost().getId();
        String redirectPath = REDIRECT_URL + POST_URL + "/"+postId;
        commentService.deleteComment(comment);
        redirectAttributes.addFlashAttribute("message",COMMENT_DELETE_SUCCESS_MESSAGE);
        return redirectPath;
    }



}
