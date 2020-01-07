package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.entity.ArticleCategory;
import com.star.site.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleCategoryController {
    @Autowired
    private ArticleCategoryService articleCategoryService;

    @GetMapping("/article/category/list")
    public String categoryList() {
        List<ArticleCategory> articleCategories = articleCategoryService.categoryList();
        return JSON.toJSONString(articleCategories);
    }

    @PostMapping("/article/category/add")
    public String addCategory(@RequestBody ArticleCategory category) {
        ArticleCategory articleCategory = articleCategoryService.addCategory(category);
        return JSON.toJSONString(articleCategory);
    }
}
