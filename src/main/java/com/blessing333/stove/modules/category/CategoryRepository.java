package com.blessing333.stove.modules.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByCategoryName(String categoryName);
    Category findByCategoryName(String categoryName);
}
