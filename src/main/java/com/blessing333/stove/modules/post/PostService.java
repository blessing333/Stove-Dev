package com.blessing333.stove.modules.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
*
* 게시글 관련 서비스를 제공하는 클래스
*
* @author Minjae Lee
* @version 0.0.0
* 작성일 2021/11/01
**/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private static final String POST_NOT_EXIST_MESSAGE = "존재하지 않거나 삭제된 게시글입니다.";

    @PostConstruct
    @Transactional
    void createWelcomePost(){
        for (int i = 0; i < 40; i++) {
            Post post = Post.createNewPost("test"+i,"test"+i,"test"+i,true);
            postRepository.save(post);
        }

    }

    @Transactional
    public Long addNewPost(PostForm postForm) {
        Post newPost = Post.createNewPost(postForm.getTitle(), postForm.getContent(),
                                            postForm.getAuthor(),postForm.isPublished());
        postRepository.save(newPost);
        return  newPost.getId();
    }

    public Post loadPostInformationFromDB(Long postId){
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException(POST_NOT_EXIST_MESSAGE));
    }

    @Transactional
    public void deletePost(Long postId){
        Post post = loadPostInformationFromDB(postId);
        postRepository.delete(post);
    }

    @Transactional
    public Long updatePost(PostEditForm postEditForm) {
        Post post = loadPostInformationFromDB(postEditForm.getId());
        post.editPostInformation(postEditForm.getTitle(),postEditForm.getContent(),
                                    postEditForm.getAuthor(), postEditForm.isPrivatePost());

        return post.getId();
    }


}
