package com.blessing333.stove.modules.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private static final String POST_NOT_EXIST_MESSAGE = "존재하지 않는 게시글입니다.";

    @Transactional
    public Long addNewPost(PostForm postForm) {
        Post newPost = Post.createNewPost(postForm.getTitle(), postForm.getContent(),
                                            postForm.getAuthor(),postForm.isPrivatePost());
        postRepository.save(newPost);
        return  newPost.getId();
    }

    public Post loadPostInformationFromDB(Long postId){
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException(POST_NOT_EXIST_MESSAGE));
    }
}
