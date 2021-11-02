//package com.blessing333.stove.modules.comment;
//
//import com.blessing333.stove.modules.post.Post;
//import com.blessing333.stove.modules.post.PostCreateFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//
//@SpringBootTest
//@Transactional
//@AutoConfigureMockMvc
//class CommentControllerTest {
//    @Autowired CommentService commentService;
//    @Autowired PostCreateFactory postCreateFactory;
//    @Autowired MockMvc mockMvc;
//
//    @DisplayName("댓글 추가 요청")
//    @Test
//    void addCommentTest() {
//        Post post = postCreateFactory.createPost();
//        mockMvc.perform(post("/comment/")
//                .param("post",post.getId().toString())
//                .param("writer","testWriter")
//                .param("content","testContent")
//                .with(csrf()))
//
//    }
//
//    @Test
//    void deleteComment() {
//    }
//}