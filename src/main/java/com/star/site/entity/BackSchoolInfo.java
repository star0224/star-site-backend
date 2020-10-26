package com.star.site.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "back_school_info")
public class BackSchoolInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String ip;

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

    @Column(nullable = false, length = 30)
    private String studentId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 20)
    private String clazz;
}
