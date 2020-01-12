package com.star.site.service;

import com.star.site.entity.ArticleCategory;
import com.star.site.repository.ArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleCategoryService {
    @Resource
    private ArticleCategoryRepository articleCategoryRepository;
    @Resource
    private ArticleService articleService;

    public List<ArticleCategory> categoryList() {
        return articleCategoryRepository.findAll();
    }


    public ArticleCategory addCategory(ArticleCategory category) {
        ArticleCategory save = articleCategoryRepository.save(category);
        return save;
    }

    public void deleteCategory(Integer id) {
        articleCategoryRepository.deleteById(id);
    }
}
