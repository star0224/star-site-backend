package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.Article;
import com.star.site.form.ArticleForm;
import com.star.site.service.ArticleService;
import com.star.site.service.RedisService;
import org.springframework.beans.BeanUtils;
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
    @Resource
    private RedisService redisService;

    // 新增/更新文章
    @PostMapping(value = "/article/add", produces = "application/json;charset=UTF-8")
    public String articleAdd(@RequestBody ArticleForm articleForm) {
        Article article = new Article();
        BeanUtils.copyProperties(articleForm, article);
        Article save = articleService.articleAdd(article);
        // 根据有无id判断是否更新
        String goal = articleForm.getId() == null ? "保存" : "更新";
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), goal + "文章成功", save.getId())) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), goal + "文章失败"));
    }

    // 查找所有文章
    @GetMapping("/article/list")
    public String articleList() {
        List<Map<String, Object>> articles = articleService.articleList();
        return articles != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取所有文章成功", articles), SerializerFeature.DisableCircularReferenceDetect) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取所有文章失败"));
    }

    // 查找所有可见文章
    @GetMapping("/article/list/public")
    public String articleList(String isPublic) {
        List<Map<String, Object>> articles = articleService.articleList(isPublic);
        return articles != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取所有文章成功", articles), SerializerFeature.DisableCircularReferenceDetect) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取所有文章失败"));
    }

    // 主页内容
    @GetMapping("/article/home")
    public String articleHome() {
        long start = System.currentTimeMillis();
        List<Map<String, Object>> articles = articleService.articleHome();
        System.out.println(System.currentTimeMillis() - start);
        return articles != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取所有文章成功", articles)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取所有文章失败"));
    }

    // 根据类别查找文章
    @GetMapping("/article/category")
    public String articleList(Integer categoryId) {
        List<Map<String, Object>> articles = articleService.articleList(categoryId);
        return articles != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取文章成功", articles), SerializerFeature.DisableCircularReferenceDetect) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取文章失败"));
    }

    // 根据类别、是否公开查找文章
    @GetMapping("/article/category/public")
    public String articleList(Integer categoryId, String isPublic) {
        List<Map<String, Object>> articles = articleService.articleList(categoryId, isPublic);
        return articles != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取文章成功", articles), SerializerFeature.DisableCircularReferenceDetect) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取文章失败"));
    }


    @GetMapping("/article")
    public String getArticleById(Long id) {
        String cache = redisService.get("article_" + id);
        if (cache != null) {
            // cache 值就是JSON格式的
            return cache;
        }
        Article article = articleService.getArticle(id);
        return article != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取文章成功", article)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取文章失败"));
    }

    @GetMapping("/article/views/add")
    public String updateArticleViewsById(Long id) {
        Article article = articleService.updateArticleViewsById(id);
        redisService.set("article_" + article.getId(), JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "缓存命中", article)));
        return article != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "更新文章访问量成功")) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "更新文章访问量失败"));
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
        Long total = articleService.articleNum();
        return total != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取统计信息成功", total)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取统计信息失败"));
    }

    @GetMapping("/article/num")
    public String articleNum() {
        List<Map<String, Object>> articleNumDTOS = articleService.getArticleNumGroupByCategory();
        return articleNumDTOS != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取数量成功", articleNumDTOS)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取数量失败"));
    }
}