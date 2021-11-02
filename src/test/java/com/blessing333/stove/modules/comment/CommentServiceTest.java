package com.blessing333.stove.modules.comment;

import com.blessing333.stove.modules.post.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired CommentService commentService;
    @Autowired CommentRepository commentRepository ;
    @Autowired PostService postService;
    @Autowired PostCreateFactory postCreateFactory;

    @DisplayName("게시글에 저장된 댓글 불러오기")
    @Test
    public void loadCommentsByPostTest() {
        //given
        Post post = postCreateFactory.createPost();
        for(int i=1;i<=5;i++) {
            String writer = "writer" + i;
            String content = "content" + i;
            CommentForm commentForm = createCommentForm(post, content, writer);
            commentService.addNewComment(commentForm);
        }
        //when
        List<Comment> comments = commentService.loadCommentsByPost(post);
        //then
        int i=1;
        assertEquals(comments.size(),5);
        for (Comment comment : comments) {
            String resultWriter = "writer"+i;
            String resultContent = "content"+i;
            assertEquals(post.getId(),comment.getPost().getId());
            assertEquals(resultWriter,comment.getWriter());
            assertEquals(resultContent,comment.getContent());
            i++;
        }
    }

    @DisplayName("댓글 추가 - 입력값 정상")
    @Test
    void addNewCommentTest() {
        //given
        Post post = postCreateFactory.createPost();
        CommentForm commentForm = createCommentForm(post,"content","writer");
        //when
        Long commentId = commentService.addNewComment(commentForm);
        //then
        assertTrue(commentRepository.existsById(commentId));
        Comment comment = commentService.loadCommentInformationFromDB(commentId);
        assertEquals(post.getId(),comment.getPost().getId());
        assertEquals(commentForm.getContent(),comment.getContent());
        assertEquals(commentForm.getWriter(),comment.getWriter());
    }

    @DisplayName("댓글 삭제")
    @Test
    void deleteComment() {
        //given
        Post post = postCreateFactory.createPost();
        CommentForm commentForm = createCommentForm(post,"content","writer");
        Long commentId = commentService.addNewComment(commentForm);
        Comment comment = commentService.loadCommentInformationFromDB(commentId);
        //when
        commentService.deleteComment(comment);
        //then
        assertFalse(commentRepository.existsById(commentId));
        assertThrows(IllegalArgumentException.class,() ->{ //삭제된 댓글 id로 댓글 조회 시도 시, IllegalArgumentException 발생
            commentService.loadCommentInformationFromDB(commentId);
        });

    }
    @DisplayName("댓글 id로 댓글 조회")
    @Test
    void loadCommentInformationFromDBTest() {
        //given
        Post post = postCreateFactory.createPost();
        CommentForm commentForm = createCommentForm(post,"content","writer");
        Long commentId = commentService.addNewComment(commentForm);
        //when
        Comment comment = commentService.loadCommentInformationFromDB(commentId);
        //then
        assertNotNull(comment);
        assertEquals(post.getId(),comment.getPost().getId());
        assertEquals("writer",comment.getWriter());
        assertEquals("content",comment.getContent());

    }

    private CommentForm createCommentForm(Post post,String content,String writer){
        CommentForm commentForm = new CommentForm();
        commentForm.setPost(post);
        commentForm.setContent(content);
        commentForm.setWriter(writer);
        return commentForm;
    }
}