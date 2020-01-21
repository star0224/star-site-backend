package com.star.site.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "article")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 20)
    private String date;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    private Integer comments = 0;

    private Integer views = 0;

    @Column(length = 1)
    private String isTop = "0";

    @Column(nullable = false, length = 1)
    private String isPublic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private ArticleCategory category;

}
