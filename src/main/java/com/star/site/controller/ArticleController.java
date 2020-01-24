package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.dto.ArticleStatisticDTO;
import com.star.site.entity.Article;
import com.star.site.form.ArticleForm;
import com.star.site.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    // 新增/更新文章
    @PostMapping(value = "/article/add", produces = "application/json;charset=UTF-8")
    public String articleAdd(@RequestBody ArticleForm articleForm) {
        Article article = new Article();
        // 默认当天日期，保存yyyy-MM-dd格式
        article.setContent(articleForm.getContent());
        article.setTitle(articleForm.getTitle());
        article.setIsPublic(articleForm.getIsPublic());
        article.setCategory(articleForm.getArticleCategory());
        article.setDate(articleForm.getDate());
        // 如果有id则更新
        String goal = "保存";
        if (articleForm.getId() != null) {
            article.setId(articleForm.getId());
            article.setViews(articleForm.getViews());
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

    // 根据类别、是否公开查找文章
    @GetMapping("/article/category/public")
    public String articleList(Integer categoryId, String isPublic) {
        List<Article> articles = articleService.articleList(categoryId, isPublic);
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

    @GetMapping("/article/delete")
    public String deleteArticleById(Long id) {
        try {
            articleService.deleteArticle(id);
            return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "删除文章成功"));
        } catch (Exception e) {
            return JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "删除文章失败，请重试"));
        }
    }

    @GetMapping("/article/update/public")
    public String updateById(Long id, String isPublic) {
        Article save = articleService.updateById(id, isPublic);
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "更新文章成功", save)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "更新文章失败"));
    }

    @GetMapping("/article/statistic")
    public String articleStatistic() {
        ArticleStatisticDTO articleStatisticDTO = articleService.articleStatistic();
        return articleStatisticDTO != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取统计信息成功", articleStatisticDTO)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取统计信息失败"));
    }

    @GetMapping("/article/num")
    public String articleNum() {
        List<Map<String, String>> articleNumDTOS = articleService.getArticleNumGroupByCategory();
        return articleNumDTOS != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取数量成功", articleNumDTOS)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取数量失败"));
    }
}
