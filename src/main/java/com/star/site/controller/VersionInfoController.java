package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.VersionInfo;
import com.star.site.service.VersionInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class VersionInfoController {
    @Resource
    private VersionInfoService versionInfoService;

    @GetMapping("/versionInfo/all")
    public String versionInfoList() {
        List<VersionInfo> versionInfos = versionInfoService.versionInfoList();
        return versionInfos != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取所有版本信息成功", versionInfos)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取所有版本信息失败"));
    }

    @PostMapping("/versionInfo/add")
    public String versionInfoAdd(@RequestBody VersionInfo versionInfo) {
        VersionInfo save = versionInfoService.versionInfoAdd(versionInfo);
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取版本信息成功", save)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取版本信息失败"));
    }
}
