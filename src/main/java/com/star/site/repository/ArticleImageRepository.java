package com.star.site.repository;

import com.star.site.entity.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    @Query(value = "delete from ArticleImage a where a.articleId = ?1")
    @Modifying
    void deleteByArticleId(Long articleId);

    List<ArticleImage> findByArticleId(Long articleId);
}
