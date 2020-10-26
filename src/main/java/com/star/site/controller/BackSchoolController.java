package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.BackSchoolInfo;
import com.star.site.repository.BackSchoolRepository;
import com.star.site.utils.StarDateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BackSchoolController {
    @Resource
    private BackSchoolRepository repository;

    @PostMapping("/backschool")
    public String saveInfo(@RequestBody BackSchoolInfo form) {
        form.setTime(StarDateUtils.getTime());
        BackSchoolInfo record = repository.findBackSchoolInfoByStudentId(form.getStudentId());
        Long id = null;
        if (record != null) {
            // 更新
            form.setId(record.getId());
        }
        BackSchoolInfo save = repository.save(form);
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "提交成功")) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "提交失败"));
    }

    @GetMapping("/getBackInfo")
    public String findAll() {
        List<BackSchoolInfo> all = repository.findAll();
        List<String> province = repository.findDistinctProvince();
        List<String> city = repository.findDistinctCity();
        List<String> district = repository.findDistinctDistrict();
        List<String> clazz = repository.findDistinctClazz();
        Map<String, Object> map = new HashMap<>();
        map.put("all", all);
        map.put("province", province);
        map.put("city", city);
        map.put("district", district);
        map.put("clazz", clazz);
        return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取成功", map));
    }
}
