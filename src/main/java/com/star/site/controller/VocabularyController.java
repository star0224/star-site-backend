package com.star.site.controller;

import com.alibaba.fastjson.JSON;
import com.star.site.common.StarResponse;
import com.star.site.common.StarResponseCode;
import com.star.site.entity.Vocabulary;
import com.star.site.service.VocabularyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class VocabularyController {
    @Resource
    private VocabularyService vocabularyService;

    @PostMapping("/vocabulary/save/all")
    public String saveAll(@RequestBody List<Vocabulary> vocabularies) {
        // 按照日期保存，保存时删除之前当天的记录
        String date = vocabularies.get(0).getDate();
        vocabularyService.deleteVocabulariesByDate(date);
        List<Vocabulary> save = vocabularyService.saveAll(vocabularies);
        return save != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "保存成功", save)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "保存失败"));
    }

    @GetMapping("/vocabulary/delete")
    public String deleteVocabulariesByDate(String date) {
        try {
            vocabularyService.deleteVocabulariesByDate(date);
            return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "删除成功"));
        } catch (Exception e) {
            return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "删除失败"));
        }
    }

    @GetMapping("/vocabulary/all")
    public String findVocabularyNumGroupByDate() {
        List<Map<String, Object>> all = vocabularyService.findVocabularyNumGroupByDate();
        return all != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取成功", all)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取失败"));
    }

    @GetMapping("/vocabulary/all/info")
    public String findAll() {
        List<Vocabulary> all = vocabularyService.findAll();
        return all != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取成功", all)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取失败"));
    }

    @GetMapping("/vocabulary/all/num")
    public String findAllNum() {
        List<Map<String, Object>> all = vocabularyService.findVocabularyNumGroupByDate();
        AtomicInteger total = new AtomicInteger();
        if (all != null) {
            all.forEach(item -> {
                String num = item.get("vocabularyNum").toString();
                total.addAndGet(Integer.valueOf(num));
            });
            return JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取成功", total));
        } else {
            return JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取失败"));
        }
    }


    @GetMapping("/vocabulary/date")
    public String findVocabulariesByDate(String date) {
        List<Vocabulary> vocabularies = vocabularyService.findVocabulariesByDate(date);
        return vocabularies != null ?
                JSON.toJSONString(new StarResponse(StarResponseCode.SUCCESS.getCode(), "获取成功", vocabularies)) :
                JSON.toJSONString(new StarResponse(StarResponseCode.ERROR.getCode(), "获取失败"));
    }

}
