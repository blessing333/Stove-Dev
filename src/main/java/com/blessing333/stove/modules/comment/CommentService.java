package com.blessing333.stove.modules.comment;

import com.blessing333.stove.modules.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private static final String COMMENT_NOT_EXIST_MESSAGE = "댓글이 존재하지 않습니다";

    public List<Comment> loadCommentsByPost(Post post){
        return commentRepository.findAllByPost(post);
    }

    @Transactional
    public Long addNewComment(CommentForm commentForm) {
        Comment comment = Comment.createComment(commentForm);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment loadCommentInformationFromDB(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(COMMENT_NOT_EXIST_MESSAGE));
    }
}
