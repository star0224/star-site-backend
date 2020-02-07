package com.star.site.service;

import com.star.site.constants.StarConstants;
import com.star.site.dto.ArticleStatisticDTO;
import com.star.site.entity.Article;
import com.star.site.repository.ArticleRepository;
import com.star.site.utils.StarDateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    public Article articleAdd(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> articleList() {
        return removeArticlesContent(articleRepository.findAll());
    }

    public List<Article> articleList(String isPublic) {
        return cutArticlesContent(articleRepository.findArticlesByIsPublic(isPublic));
    }

    public List<Article> articleList(Integer categoryId) {
        return removeArticlesContent(articleRepository.findArticlesByCategoryId(categoryId));
    }

    public List<Article> articleList(Integer categoryId, String isPublic) {
        return removeArticlesContent(articleRepository.findArticlesByCategoryIdAndIsPublic(categoryId, isPublic));
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

    // 对内容进行删减，获取列表时只需展示部分内容
    public List<Article> cutArticlesContent(List<Article> articles) {
        articles.forEach(article -> {
            if (article.getContent().length() >= 50) {
                article.setContent(article.getContent().substring(0, 50));
            }
        });
        return articles;
    }

    // 对内容进行移除，获取部分列表时不需展示内容
    public List<Article> removeArticlesContent(List<Article> articles) {
        articles.forEach(article -> {
            article.setContent("");
        });
        return articles;
    }
}
