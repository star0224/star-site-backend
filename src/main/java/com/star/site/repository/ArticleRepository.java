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

    @Query(value = "select count(article.id) as articleNum, category.name as categoryName from article, article_category category where article.category_id = category.id and is_public = '1' group by article.category_id order by category.name asc", nativeQuery = true)
    List<Map<String, Object>> findArticlesNumGroupByCategory();

    @Query(value = "select article.id, article_category.name as categoryName, word_count as wordCount, date, is_top as isTop, title, views, category_id as categoryId, is_public as isPublic, comments from article, article_category where article.category_id = article_category.id order by article.date desc, id desc", nativeQuery = true)
    List<Map<String, Object>> findAllExcludeContent();

    @Query(value = "select article.id, article_category.name as categoryName, word_count as wordCount, date, is_top as isTop, title, views, category_id as categoryId, is_public as isPublic, comments from article, article_category where is_public = 1 and article.category_id = article_category.id order by article.date desc, id desc", nativeQuery = true)
    List<Map<String, Object>> findPublicExcludeContent();

    @Query(value = "select article.id, article_category.name as categoryName, word_count as wordCount, date, is_top as isTop, title, views, category_id as categoryId, is_public as isPublic, comments from article, article_category where is_public = 1 and article_category.id = ?1 and article.category_id = article_category.id order by article.date desc, id desc", nativeQuery = true)
    List<Map<String, Object>> findPublicExcludeContentByCategoryId(Integer categoryId);

    @Query(value = "select article.id, article_category.name as categoryName, word_count as wordCount, date, is_top as isTop, title, views, category_id as categoryId, is_public as isPublic, comments from article, article_category where article_category.id = ?1 and article.category_id = article_category.id order by article.date desc, id desc", nativeQuery = true)
    List<Map<String, Object>> findAllExcludeContentByCategoryId(Integer categoryId);

    @Query(value = "select count(id) from article", nativeQuery = true)
    Long countNum();

    @Query(value = "select article.id, article_category.name as categoryName, word_count as wordCount, description, date, is_top as isTop, title, views, category_id as categoryId, is_public as isPublic, comments from article, article_category where is_public = 1 and article.category_id = article_category.id order by article.date desc, id desc", nativeQuery = true)
    List<Map<String, Object>> findArticleHome();

    @Query(value = "select article.id, article_category.name as categoryName, word_count as wordCount, content, description, date, is_top as isTop, title, views, category_id as categoryId, is_public as isPublic, comments from article, article_category where article.category_id = article_category.id and article.id = ?1", nativeQuery = true)
    Map<String, Object> starFindArticleById(Long id);
}
