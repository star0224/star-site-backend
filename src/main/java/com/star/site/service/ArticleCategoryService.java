package com.star.site.service;

import com.star.site.entity.ArticleCategory;
import com.star.site.repository.ArticleCategoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArticleCategoryService {
    @Resource
    private ArticleCategoryRepository articleCategoryRepository;
    @Resource
    private ArticleService articleService;

    public List<Map<String, Object>> categoryList() {
        return articleCategoryRepository.findAllCategory();
    }


    public ArticleCategory addCategory(ArticleCategory category) {
        ArticleCategory save = articleCategoryRepository.save(category);
        return save;
    }

    public void deleteCategory(Integer id) {
        articleCategoryRepository.deleteById(id);
    }
}
