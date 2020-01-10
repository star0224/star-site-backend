package com.star.site.service;

import com.star.site.entity.Article;
import com.star.site.entity.ArticleCategory;
import com.star.site.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article articleAdd(Article article) {
        Article save = articleRepository.save(article);
        return save;
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

    public Article getArticle(Long id) {
        return articleRepository.findById(id).get();
    }

}
