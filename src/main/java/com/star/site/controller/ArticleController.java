package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
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
        article.setIsPublic(articleForm.getIsPublic());
        article.setCategory(articleForm.getArticleCategory());
        // 如果有id则更新
        String goal = "保存";
        if (articleForm.getId() != null) {
            article.setId(articleForm.getId());
            goal = "更新";
        }
        Article save = articleService.articleAdd(article);
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), goal + "文章成功", save)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), goal + "文章失败"));
    }

    // 查找所有文章
    @GetMapping("/article/list")
    public String articleList() {
        List<Article> articles = articleService.articleList();
        return articles != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取所有文章成功", articles), SerializerFeature.DisableCircularReferenceDetect) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取所有文章失败"));
    }

    // 查找所有可见文章
    @GetMapping("/article/list/public")
    public String articleList(String isPublic) {
        List<Article> articles = articleService.articleList(isPublic);
        return articles != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取所有文章成功", articles), SerializerFeature.DisableCircularReferenceDetect) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取所有文章失败"));
    }

    // 根据类别查找文章
    @GetMapping("/article/category")
    public String articleList(Integer categoryId) {
        List<Article> articles = articleService.articleList(categoryId);
        return articles != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取文章成功", articles), SerializerFeature.DisableCircularReferenceDetect) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取文章失败"));
    }

    // 根据id查找文章，查找后访问量自增
    @GetMapping("/article")
    public String getArticleById(Long id) {
        Article article = articleService.getArticle(id);
        article.setViews(article.getViews() + 1);
        Article save = articleService.articleAdd(article);
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取文章成功", save)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取文章失败"));

    }

}
