package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.ArticleImage;
import com.star.site.service.ArticleImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ArticleImageController {
    @Resource
    private ArticleImageService imageService;

    @GetMapping("/article/images")
    public String getImagesByArticleId(Long articleId) {
        List<ArticleImage> images = imageService.getImagesByArticleId(articleId);
        return images != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取图片成功", images)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取图片失败"));
    }

    @PostMapping("/article/images/save")
    public String saveImages(@RequestBody List<ArticleImage> images) {
        if (images == null || images.size() == 0) {
            return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "无图片需要保存"));
        }
        // 每次对文章保存/更新都是先删除之前的图片，再保存新的图片
        imageService.deleteImageByArticleId(images.get(0).getArticleId());
        imageService.saveImages(images);
        return images != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "保存图片成功")) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "保存图片失败"));
    }
}
