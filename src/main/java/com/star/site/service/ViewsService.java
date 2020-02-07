package com.star.site.service;

import com.star.site.entity.Views;
import com.star.site.repository.ViewsRepository;
import com.star.site.utils.StarDateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ViewsService {
    @Resource
    private ViewsRepository viewsRepository;

    public Long count() {
        return viewsRepository.count();
    }

    public Views save(Views views) {
        return viewsRepository.save(views);
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
