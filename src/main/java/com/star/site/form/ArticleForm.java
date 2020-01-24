package com.star.site.form;

import com.star.site.entity.ArticleCategory;
import lombok.Data;

@Data
public class ArticleForm {
    private Long id;
    private String title;
    private String date;
    private String content;
    private Integer comments;
    private Integer views;
    private String isTop;
    private String isPublic;
    private ArticleCategory articleCategory;
}
