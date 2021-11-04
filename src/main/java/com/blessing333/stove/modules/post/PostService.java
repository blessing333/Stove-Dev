package com.blessing333.stove.modules.post;

import com.blessing333.stove.modules.category.Category;
import com.blessing333.stove.modules.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
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
    private final CategoryRepository categoryRepository;
    private static final String POST_NOT_EXIST_MESSAGE = "존재하지 않거나 삭제된 게시글입니다.";

    @PostConstruct
    @Transactional
    void createWelcomePost(){
        Category category = new Category("기타");
        categoryRepository.save(category);
        Post post = Post.createNewPost("환영합니다!","블로그에 오신것을 환영합니다!","Admin",true,category,null);
        postRepository.save(post);

    }

    @Transactional
    public Long addNewPost(PostForm postForm) {
        Category category = categoryRepository.findById(postForm.getCategory()).get();
        Post newPost = Post.createNewPost(postForm.getTitle(), postForm.getContent(),
                                            postForm.getAuthor(),postForm.isPublished(),category, postForm.getThumbnail());
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
        Category category = categoryRepository.findById(postEditForm.getCategory()).get();
        Post post = loadPostInformationFromDB(postEditForm.getId());
        post.editPostInformation(postEditForm.getTitle(),postEditForm.getContent(),
                                    postEditForm.getAuthor(), postEditForm.isPublished(),
                                    category, postEditForm.getThumbnail());
        return post.getId();
    }


}
