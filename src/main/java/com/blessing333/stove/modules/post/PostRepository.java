package com.blessing333.stove.modules.post;

import com.blessing333.stove.modules.category.Category;
import com.blessing333.stove.modules.post.queryDSL.PostRepositoryExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryExtension {
    Page<Post> findByPublished(boolean published, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);
    boolean existsByCategory(Category category);
}
