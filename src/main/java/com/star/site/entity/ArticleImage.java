package com.star.site.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "article_image")
@Data
public class ArticleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long articleId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String image;
}
