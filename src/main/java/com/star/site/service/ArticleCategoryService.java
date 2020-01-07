package com.star.site.service;

import com.star.site.entity.ArticleCategory;
import com.star.site.repository.ArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleCategoryService {
    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    public List<ArticleCategory> categoryList() {
        return convert(articleCategoryRepository.findAll());
    }


    public ArticleCategory addCategory(ArticleCategory category) {
        ArticleCategory save = articleCategoryRepository.save(category);
        return save;
    }

    private List<ArticleCategory> convert(List<ArticleCategory> categories) {
        categories.stream().map(category -> {
            category.setArticleList(null);
            return category;
        }).collect(Collectors.toList());
        return categories;
    }
}
