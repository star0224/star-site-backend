package com.star.site.form;

import com.star.site.constants.StarConstants;
import com.star.site.entity.ArticleCategory;
import lombok.Data;

@Data
public class ArticleForm {
    private Long id;
    private String title;
    private String date;
    private String content;
    private Integer views = 0;
    private Integer wordCount;
    private String isTop = StarConstants.ARTICLE_NOT_TOP;
    private String isPublic;
    private String description;
    private ArticleCategory category;
}
