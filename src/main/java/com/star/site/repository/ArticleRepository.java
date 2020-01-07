package com.star.site.repository;

import com.star.site.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticlesByCategoryId(Integer categoryId);


}
