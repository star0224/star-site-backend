package com.star.site.dto;

import lombok.Data;

@Data
public class ArticleDTO {
    private Long id;
    private String categoryName;
    private String date;
    private String isTop;
    private String title;
    private Integer views;
    private String isPublic;
    private Integer comments;
    private Integer categoryId;
}
