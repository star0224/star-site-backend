package com.star.site.repository;

import com.star.site.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, Integer> {
    @Query(value = "select id, name from article_category order by name asc" , nativeQuery = true)
    List<Map<String, Object>> findAllCategory();
}
