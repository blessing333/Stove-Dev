package com.blessing333.stove.modules.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAllByPublished(boolean published, PageRequest pageRequest);

}
