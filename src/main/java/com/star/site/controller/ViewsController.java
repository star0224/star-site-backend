package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.Views;
import com.star.site.form.ViewsForm;
import com.star.site.service.ViewsService;
import com.star.site.utils.StarDateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class ViewsController {
    @Resource
    private ViewsService viewsService;

    @GetMapping("/views/all/num")
    public String count() {
        Long count = viewsService.count();
        return count != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取成功", count)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取失败"));
    }

    @PostMapping("/views/add")
    public String add(@RequestBody ViewsForm viewsForm) {
        if (viewsForm.getIp() == null) {
            return JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "数据为空"));
        }
        // 先查找10分钟内有无访问记录，若有不增加
        if (viewsService.validate(viewsForm.getIp())) {
            return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "频繁访问只记录一次"));
        }

        Views views = new Views();
        BeanUtils.copyProperties(viewsForm, views);
        views.setDate(StarDateUtils.getDate());
        views.setTime(StarDateUtils.getTime());
        Views save = viewsService.save(views);
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "保存成功", save)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "保存失败"));
    }

    @GetMapping("/views/province")
    public String countProvince() {
        List<Map<String, Object>> provinces = viewsService.countProvince();
        return provinces != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "查找成功", provinces)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "查找失败"));
    }

    @GetMapping("/views/city")
    public String countCity(String province) {
        List<Map<String, Object>> cities = viewsService.countCity(province);
        return cities != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "查找成功", cities)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "查找失败"));
    }

    @GetMapping("/views/last7Days/num")
    public String countLast7DaysViews() {
        List<Map<String, Object>> views = viewsService.countLast7DaysViews();
        return views != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "查找成功", views)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "查找失败"));
    }
}
