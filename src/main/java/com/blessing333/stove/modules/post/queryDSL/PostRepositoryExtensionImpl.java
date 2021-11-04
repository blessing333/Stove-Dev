package com.blessing333.stove.modules.post.queryDSL;

import com.blessing333.stove.modules.post.Post;
import com.blessing333.stove.modules.post.QPost;
import com.blessing333.stove.modules.post.queryDSL.PostRepositoryExtension;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PostRepositoryExtensionImpl extends QuerydslRepositorySupport implements PostRepositoryExtension {
    public PostRepositoryExtensionImpl() {
        super(Post.class);
    }

    @Override
    public Page<Post> findByKeyword(String keyword, Pageable pageable) {
        QPost post = QPost.post;
        JPQLQuery<Post> query = from(post).where(post.published.isTrue()
                .and(post.title.containsIgnoreCase(keyword))
                .or(post.content.containsIgnoreCase(keyword)))
                .distinct();
        JPQLQuery<Post> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Post> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }
}
