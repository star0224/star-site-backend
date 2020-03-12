package com.star.site.service;

import com.star.site.entity.ArticleImage;
import com.star.site.repository.ArticleImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleImageService {
    @Resource
    private ArticleImageRepository imageRepository;

    public void deleteImageByArticleId(Long articleId) {
        imageRepository.deleteByArticleId(articleId);
    }

    public List<ArticleImage> saveImages(List<ArticleImage> imageList) {
        return imageRepository.saveAll(imageList);
    }

    public List<ArticleImage> getImagesByArticleId(Long articleId) {
        return imageRepository.findByArticleId(articleId);
    }
}
