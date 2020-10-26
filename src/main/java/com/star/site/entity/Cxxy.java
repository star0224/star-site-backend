package com.star.site.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cxxy")
public class Cxxy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String studentId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 20)
    private String clazz;
}
