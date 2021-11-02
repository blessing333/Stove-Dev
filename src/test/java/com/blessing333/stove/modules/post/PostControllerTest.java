package com.blessing333.stove.modules.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired PostService postService;
    @Autowired PostCreateFactory postCreateFactory;

    @Test
    @DisplayName("게시글 등록화면 생성")
    void createPostFormView() throws Exception{
        mockMvc.perform(get("/post/post-form"))
                .andExpect(view().name("post/post-form"));
    }

    @Test
    @DisplayName("게시글 조회 화면 생성")
    void createPostView() throws Exception{
        PostForm postForm = postCreateFactory.createPostForm();
        Long postId = postService.addNewPost(postForm);
        mockMvc.perform(get("/post/" + postId))
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("post"))
                .andExpect(view().name("post/post"));
    }

    @Test
    void addNewPost() throws Exception{
        PostForm postForm =  postCreateFactory.createPostForm();
        mockMvc.perform(post("/post")
                .param("title",postForm.getTitle())
                .param("author", postForm.getAuthor())
                .param("content",postForm.getContent())
                .param("published",postForm.isPrivatePost()+"")
                .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(model().hasNoErrors());
    }
}