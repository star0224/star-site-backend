package com.star.site.service;

import com.star.site.constants.StarConstants;
import com.star.site.dto.ArticleStatisticDTO;
import com.star.site.entity.Article;
import com.star.site.entity.ArticleCategory;
import com.star.site.repository.ArticleRepository;
import com.star.site.utils.StarDateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    public Article articleAdd(Article article) {
        return articleRepository.save(article);
    }

    public List<Map<String, Object>> articleList() {
        return articleRepository.findAllExcludeContent();
    }

    public List<Map<String, Object>> articleHome() {
        return articleRepository.findArticleHome();
    }

    public List<Map<String, Object>> articleList(String isPublic) {
        return isPublic != StarConstants.ARTICLE_PUBLIC ?
                articleRepository.findAllExcludeContent() : articleRepository.findPublicExcludeContent();
    }

    public List<Map<String, Object>> articleList(Integer categoryId) {
        return articleRepository.findAllExcludeContentByCategoryId(categoryId);
    }

    public List<Map<String, Object>> articleList(Integer categoryId, String isPublic) {
        return isPublic != StarConstants.ARTICLE_PUBLIC ?
                articleRepository.findAllExcludeContentByCategoryId(categoryId) : articleRepository.findPublicExcludeContentByCategoryId(categoryId);
    }

    // 根据id查找文章，查找后访问量自增
    public Article getArticle(Long id) {
        Map<String, Object> articleMap = articleRepository.starFindArticleById(id);
        Article article = new Article();
        article.setId(((BigInteger) articleMap.get("id")).longValue());
        article.setDate((String) articleMap.get("date"));
        article.setDescription((String) articleMap.get("description"));
        article.setIsPublic((String) articleMap.get("isPublic"));
        article.setIsTop((String) articleMap.get("isTop"));
        article.setWordCount((Integer) articleMap.get("wordCount"));
        article.setViews((Integer) articleMap.get("views") + 1);
        article.setContent((String) articleMap.get("content"));
        article.setTitle((String) articleMap.get("title"));
        article.setComments((Integer) articleMap.get("comments"));
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setId((Integer) articleMap.get("categoryId"));
        articleCategory.setName((String) articleMap.get("categoryName"));
        article.setCategory(articleCategory);
        return article;
    }

    public Article updateArticleViewsById(Long id) {
        Map<String, Object> articleMap = articleRepository.starFindArticleById(id);
        Article article = new Article();
        article.setId(((BigInteger) articleMap.get("id")).longValue());
        article.setDate((String) articleMap.get("date"));
        article.setDescription((String) articleMap.get("description"));
        article.setIsPublic((String) articleMap.get("isPublic"));
        article.setIsTop((String) articleMap.get("isTop"));
        article.setWordCount((Integer) articleMap.get("wordCount"));
        article.setViews((Integer) articleMap.get("views") + 1);
        article.setContent((String) articleMap.get("content"));
        article.setTitle((String) articleMap.get("title"));
        article.setComments((Integer) articleMap.get("comments"));
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setId((Integer) articleMap.get("categoryId"));
        articleCategory.setName((String) articleMap.get("categoryName"));
        article.setCategory(articleCategory);
        articleAdd(article);
        return article;
    }

    public ArticleStatisticDTO articleStatistic() {
        ArticleStatisticDTO articleStatisticDTO = new ArticleStatisticDTO();
        List<Article> articles = articleRepository.findArticlesByIsPublic(StarConstants.ARTICLE_PUBLIC);

        String today = StarDateUtils.getDate();
        String thisWeekStart = StarDateUtils.getWeekStart();
        String thisWeekEnd = StarDateUtils.getWeekEnd();
        String thisMonthStart = StarDateUtils.getMonthStart();
        String thisMonthEnd = StarDateUtils.getMonthEnd();

        articles.forEach(article -> {
            articleStatisticDTO.articleTotalGAI();
            String articleDate = article.getDate();
            if (articleDate.compareTo(today) == 0) {
                articleStatisticDTO.articleDayGAI();
            }
            if (articleDate.compareTo(thisWeekStart) >= 0 && articleDate.compareTo(thisWeekEnd) <= 0) {
                articleStatisticDTO.articleWeekGAI();
            }
            if (articleDate.compareTo(thisMonthStart) >= 0 && articleDate.compareTo(thisMonthEnd) <= 0) {
                articleStatisticDTO.articleMonthGAI();
            }
        });
        return articleStatisticDTO;
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public Article updateById(Long id, String isPublic) {
        Article article = articleRepository.findById(id).get();
        article.setIsPublic(isPublic);
        Article save = articleRepository.save(article);
        return save;
    }

    public Long articleNum() {
        return articleRepository.countNum();
    }

    public List<Map<String, Object>> getArticleNumGroupByCategory() {
        return articleRepository.findArticlesNumGroupByCategory();
    }


}
