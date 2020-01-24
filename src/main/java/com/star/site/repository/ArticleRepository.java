package com.star.site.repository;

import com.star.site.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticlesByCategoryId(Integer categoryId);

    List<Article> findArticlesByIsPublic(String isPublic);

    List<Article> findArticlesByCategoryIdAndIsPublic(Integer categoryId, String isPublic);

    @Query(value = "delete from Article a where a.id = ?1")
    @Modifying
    void deleteById(Long id);

    @Query(value = "select count(article.id) as articleNum, category.name as categoryName from article, article_category category where article.category_id = category.id group by article.category_id", nativeQuery = true)
    List<Map<String, String>> findArticlesNumGroupByCategory();
}
