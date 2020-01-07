package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.entity.Article;
import com.star.site.form.ArticleForm;
import com.star.site.service.ArticleService;
import com.star.site.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // 新增/更新文章
    @PostMapping(value = "/article/add", produces = "application/json;charset=UTF-8")
    public String articleAdd(@RequestBody ArticleForm articleForm) {
        Article article = new Article();
        // 默认当天日期，保存yyyy-MM-dd格式
        article.setDate(DateUtils.getDate());
        article.setContent(articleForm.getContent());
        article.setTitle(articleForm.getTitle());
        article.setIsTop(articleForm.getIsTop());
        article.setViews(articleForm.getViews());
        article.setLikes(articleForm.getLikes());
        article.setCategory(articleForm.getArticleCategory());
        // 如果有id则更新
        if (articleForm.getId() != null) {
            article.setId(articleForm.getId());
        }
        return articleService.articleAdd(article);
    }

    // 查找所有文章
    @GetMapping("/article/list")
    public String articleList() {
        List<Article> articles = articleService.articleList();
        return JSON.toJSONString(articles);
    }

    // 根据类别查找文章
    @GetMapping("/article/category")
    public String articleList(Integer categoryId) {
        List<Article> articles = articleService.articleList(categoryId);
        return JSON.toJSONString(articles);
    }

    // 根据id查找文章
    @GetMapping("/article")
    public String getArticleById(Long id) {
        Article article = articleService.getArticle(id);
        return article != null ? JSON.toJSONString(article) : "failed";
    }
}
