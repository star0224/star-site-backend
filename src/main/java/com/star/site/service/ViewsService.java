package com.star.site.service;

import com.star.site.entity.Views;
import com.star.site.repository.ViewsRepository;
import com.star.site.utils.StarDateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ViewsService {
    @Resource
    private ViewsRepository viewsRepository;

    public Long count() {
        return viewsRepository.countTotal();
    }

    public Views save(Views views) {
        return viewsRepository.save(views);
    }

    public List<Map<String, Object>> countCity(String province) {
        return viewsRepository.countCity(province);
    }

    public List<Map<String, Object>> countProvince() {
        return viewsRepository.countProvince();
    }

    public List<Map<String, Object>> countLast7DaysViews() {
        return viewsRepository.countLast7DaysViews();
    }

    public boolean validate(String ip) {
        Views lastView = viewsRepository.findFirstByIpOrderByIdDesc(ip);
        if (lastView == null) {
            return false;
        }
        String time = lastView.getTime();
        long standard = Long.parseLong(time.replaceAll("[-: ]", "")) + 3000;
        long now = Long.parseLong(StarDateUtils.getTime().replaceAll("[-: ]", ""));
        return standard > now;
    }

}
