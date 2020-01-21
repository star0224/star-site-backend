package com.star.site.repository;

import com.star.site.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticlesByCategoryId(Integer categoryId);

    List<Article> findArticlesByIsPublic(String isPublic);

    List<Article> findArticlesByCategoryIdAndIsPublic(Integer categoryId, String isPublic);

    @Query(value = "delete from Article a where a.id = ?1")
    @Modifying
    void deleteById(Long id);
}
