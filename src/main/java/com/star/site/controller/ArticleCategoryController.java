package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.ArticleCategory;
import com.star.site.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ArticleCategoryController {
    @Resource
    private ArticleCategoryService articleCategoryService;

    @GetMapping("/article/category/list")
    public String categoryList() {
        List<ArticleCategory> articleCategories = articleCategoryService.categoryList();
        return articleCategories != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取所有分类成功", articleCategories))
                : JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取所有分类失败"));
    }

    @PostMapping("/article/category/add")
    public String addCategory(@RequestBody ArticleCategory category) {
        ArticleCategory articleCategory = articleCategoryService.addCategory(category);
        return articleCategory != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "增加文章分类成功", articleCategory))
                : JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "增加文章分类失败"));
    }

    @GetMapping("/article/category/delete")
    public String deleteCategory(Integer id) {
        try {
            articleCategoryService.deleteCategory(id);
            return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "删除文章分类及其文章成功"));
        } catch (Exception e) {
            return JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "删除文章分类及其文章失败"));
        }
    }
 }
