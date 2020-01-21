package com.star.site.entity;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "versionInfo")
public class VersionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 20)
    private String version;
}
