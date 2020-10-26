package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.Cxxy;
import com.star.site.repository.CxxyRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CxxyController {
    @Resource
    private CxxyRepository cxxyRepository;

    @PostMapping("/cxxy")
    public String findCxxyByStudentId(@RequestBody Cxxy form){
        Cxxy cxxy = cxxyRepository.findCxxyByStudentIdAndName(form.getStudentId(), form.getName());
        return cxxy != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "验证成功", cxxy)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "验证失败"));
    }

}
