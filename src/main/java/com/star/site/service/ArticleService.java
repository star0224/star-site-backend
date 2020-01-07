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

    public String articleAdd(Article article) {
        Article save = articleRepository.save(article);
        return save != null ? "success" : "failed" ;
    }

    public List<Article> articleList() {
        return convert(articleRepository.findAll());
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).get();
    }

    public List<Article> articleList(Integer categoryId) {
        return convert(articleRepository.findArticlesByCategoryId(categoryId));
    }

    private List<Article> convert(List<Article> articles) {
        articles.stream().map(article -> {
            article.getCategory().setArticleList(null);
            return article;
        }).collect(Collectors.toList());
        return articles;
    }
}
