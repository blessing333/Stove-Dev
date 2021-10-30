package com.blessing333.stove.modules.post;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired private PostService postService;
    @Autowired PostRepository postRepository;
    private static final String POST_TITLE = "테스트 게시글 제목";
    private static final String POST_AUTHOR = "테스트 게시글 작성자";
    private static final String POST_CONTENT = "테스트 게시글 내용";
    private static final boolean POST_PUBLISH = false;

    @AfterEach
    public void resetPostDB(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 생성 - 입력값 정상")
    public void addPostTest(){
        //given
        PostForm postForm = createPostForm();
        //when
        Long id = postService.addNewPost(postForm);
        //then
        assertThat(id).isNotNull();
        Post post = postRepository.findById(id).get();
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo(POST_TITLE);
        assertThat(post.getAuthor()).isEqualTo(POST_AUTHOR);
        assertThat(post.getContent()).isEqualTo(POST_CONTENT);
        assertThat(post.isPublished()).isEqualTo(POST_PUBLISH);
        assertThat(post.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("DB에서 게시글 불러오기 - 입력값 정상")
    public void loadPostInformationFromDBTest(){
        // given
        PostForm postForm = createPostForm();
        Long id = postService.addNewPost(postForm);

        // when
        Post post = postService.loadPostInformationFromDB(id);
        // then
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo(postForm.getTitle());
        assertThat(post.getAuthor()).isEqualTo(postForm.getAuthor());
        assertThat(post.getContent()).isEqualTo(postForm.getContent());
        assertThat(post.isPublished()).isEqualTo(postForm.isPrivatePost());
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

    static public PostForm createPostForm(){
        PostForm postForm = new PostForm();
        postForm.setTitle(POST_TITLE);
        postForm.setAuthor(POST_AUTHOR);
        postForm.setContent(POST_CONTENT);
        postForm.setPrivatePost(POST_PUBLISH);
        return postForm;
    }
}