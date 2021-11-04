package com.blessing333.stove.modules.post.queryDSL;


import com.blessing333.stove.modules.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface PostRepositoryExtension {
    Page<Post> findByKeyword(String keyword, Pageable pageable);
}
