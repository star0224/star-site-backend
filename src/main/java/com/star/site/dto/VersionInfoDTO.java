package com.star.site.dto;

import lombok.Data;

import java.util.List;

@Data
public class VersionInfoDTO {
    private String version;

    private String Date;

    private List<String> contents;

}
