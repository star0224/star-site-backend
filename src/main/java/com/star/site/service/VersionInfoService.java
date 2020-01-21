package com.star.site.service;

import com.star.site.entity.VersionInfo;
import com.star.site.repository.VersionInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VersionInfoService {
    @Resource
    private VersionInfoRepository versionInfoRepository;

    public List<VersionInfo> versionInfoList() {
        return versionInfoRepository.findAll();
    }

    public VersionInfo versionInfoAdd(VersionInfo versionInfo) {
        return versionInfoRepository.save(versionInfo);
    }
}
