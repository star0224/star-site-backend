package com.star.site.service;

import com.star.site.dto.VersionInfoDTO;
import com.star.site.entity.VersionInfo;
import com.star.site.repository.VersionInfoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class VersionInfoService {
    @Resource
    private VersionInfoRepository versionInfoRepository;

    public List<VersionInfo> versionInfosAdd(List<VersionInfo> versionInfos) {
        versionInfoRepository.truncateTable();
        List<VersionInfo> saves = versionInfoRepository.saveAll(versionInfos);
        return saves;
    }

    public List<VersionInfoDTO> versionInfoList() {
        List<VersionInfo> all = versionInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "version"));
        List<String> versions = new ArrayList<>();
        List<VersionInfoDTO> versionInfoDTOS = new ArrayList<>();
        all.forEach(item -> {
            if (!versions.contains(item.getVersion())) {
                versions.add(item.getVersion());
            }
        });
        versions.forEach(version -> {
            VersionInfoDTO versionInfoDTO = new VersionInfoDTO();
            versionInfoDTO.setVersion(version);
            List<String> versionContents = new ArrayList<>();
            versionInfoDTO.setContents(versionContents);
            all.forEach(versionInfo -> {
                if (versionInfo.getVersion().equals(version)) {
                    versionContents.add(versionInfo.getContent());
                    versionInfoDTO.setDate(versionInfo.getDate());
                }
            });
            versionInfoDTOS.add(versionInfoDTO);
        });
        return versionInfoDTOS;
    }

    public VersionInfo versionInfoAdd(VersionInfo versionInfo) {
        return versionInfoRepository.save(versionInfo);
    }
}
