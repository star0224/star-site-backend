package com.star.site.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "vocabulary")
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String english;

    @Column(nullable = false, length = 50)
    private String chinese;

    @Column(nullable = false, length = 50)
    private String phonetic;

    @Column(nullable = false, length = 20)
    private String date;

}
