package com.star.site.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Long articleId;

    private Integer parentCommentId;

    @Column(length = 100)
    private String mail;

    @Column(nullable = false, length = 1024)
    private String content;

    @Column(nullable = false, length = 20)
    private String time;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(length = 20)
    private String ip;

    @Column(length = 10)
    private String country;

    @Column(length = 10)
    private String city;

    @Column(length = 10)
    private String province;

    @Column(length = 10)
    private String district;

    @Column(length = 10)
    private String replyName;
}
