package com.star.site.service;

import com.star.site.entity.Article;
import com.star.site.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
}
