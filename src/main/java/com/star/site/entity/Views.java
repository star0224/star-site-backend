package com.star.site.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "views")
public class Views {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String ip;

    @Column(nullable = false, length = 20)
    private String date;

    @Column(nullable = false, length = 20)
    private String time;

    @Column(length = 10)
    private String country;

    @Column(length = 10)
    private String city;

    @Column(length = 10)
    private String province;

    @Column(length = 10)
    private String district;

    @Column(length = 10)
    private String lat;

    @Column(length = 10)
    private String lng;
}
