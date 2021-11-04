package com.blessing333.stove.modules.comment;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.blessing333.stove.infra.config.UrlConfig.*;
/**
*
* 댓글과 관련된 요청을 처리하는 컨트롤러
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/05
**/
@Controller
@RequiredArgsConstructor
public class CommentController {
    private static final String COMMENT_DELETE_SUCCESS_MESSAGE = "댓글이 삭제되었습니다.";
    private final CommentService commentService;

    /*
    * 새로운 댓글 생성 요청을 처리하는 메소드. 댓글을 생성한 후, 해당 댓글이 생성된 게시글 조회 화면으로 redirect
    * */
    @PostMapping(COMMENT_URL)
    public String addComment(CommentForm commentForm){
        commentService.addNewComment(commentForm);
        String postPath = "/" + commentForm.getPost().getId();
        return REDIRECT_URL + POST_URL + postPath;
    }
    /*
     * 댓글 삭제 요청을 처리하는 메소드. 댓글을 삭제한 후, 해당 댓글이 생성된 게시글 조회 화면으로 redirect
     * */
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
