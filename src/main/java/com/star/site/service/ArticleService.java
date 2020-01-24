package com.star.site.service;

import com.star.site.constants.StarConstants;
import com.star.site.dto.ArticleStatisticDTO;
import com.star.site.entity.Article;
import com.star.site.repository.ArticleRepository;
import com.star.site.utils.StarDateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    public Article articleAdd(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> articleList() {
        return articleRepository.findAll();
    }

    public List<Article> articleList(String isPublic) {
        return articleRepository.findArticlesByIsPublic(isPublic);
    }

    public List<Article> articleList(Integer categoryId) {
        return articleRepository.findArticlesByCategoryId(categoryId);
    }

    public List<Article> articleList(Integer categoryId, String isPublic) {
        return articleRepository.findArticlesByCategoryIdAndIsPublic(categoryId, isPublic);
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).get();
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

    public List<Map<String, String>> getArticleNumGroupByCategory() {
        return articleRepository.findArticlesNumGroupByCategory();
    }
}
