package com.blessing333.stove.modules.post;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreateFactory {
    public static final String POST_TITLE = "테스트 게시글 제목";
    public static final String POST_AUTHOR = "테스트 게시글 작성자";
    public static final String POST_CONTENT = "테스트 게시글 내용";
    public static final boolean POST_PUBLISH = true;

    @Autowired PostService postService;
    public Post createPost(){
        PostForm postForm = createPostForm();
        Long postId =  postService.addNewPost(postForm);
        return postService.loadPostInformationFromDB(postId);
    }

    public PostForm createPostForm(){
        PostForm postForm = new PostForm();
        postForm.setTitle(POST_TITLE);
        postForm.setAuthor(POST_AUTHOR);
        postForm.setContent(POST_CONTENT);
        postForm.setPublished(POST_PUBLISH);
        return postForm;
    }
}
