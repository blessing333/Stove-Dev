package com.blessing333.stove.modules.post;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class PostServiceTest {
    @Autowired private PostService postService;
    @Autowired PostRepository postRepository;
    @Autowired PostCreateFactory postCreateFactory;

    @AfterEach
    public void resetPostDB(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 생성 - 입력값 정상")
    public void addPostTest(){
        //given
        PostForm postForm = postCreateFactory.createPostForm();
        //when
        Long id = postService.addNewPost(postForm);
        //then
        assertThat(id).isNotNull();
        Post post = postRepository.findById(id).get();
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo(postForm.getTitle());
        assertThat(post.getAuthor()).isEqualTo(postForm.getAuthor());
        assertThat(post.getContent()).isEqualTo(postForm.getContent());
        assertThat(post.isPrivatePost()).isEqualTo(postForm.isPrivatePost());
    }

    @Test
    @DisplayName("DB에서 게시글 불러오기 - 입력값 정상")
    public void loadPostInformationFromDBTest(){
        // given
        PostForm postForm = postCreateFactory.createPostForm();
        Long id = postService.addNewPost(postForm);

        // when
        Post post = postService.loadPostInformationFromDB(id);
        // then
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo(postForm.getTitle());
        assertThat(post.getAuthor()).isEqualTo(postForm.getAuthor());
        assertThat(post.getContent()).isEqualTo(postForm.getContent());
        assertThat(post.isPrivatePost()).isEqualTo(postForm.isPrivatePost());
    }

    @Test
    @DisplayName("DB에서 게시글 불러오기 - 존재하지 않는 ID")
    public void loadPostInformationFromDBTestWithWrongId(){
        // given
        Long invalidId = -1L;
        // when
        Assertions.assertThrows(IllegalArgumentException.class,() ->
            postService.loadPostInformationFromDB(invalidId)
        );
    }


}