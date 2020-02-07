package com.star.site.service;

import com.star.site.entity.Vocabulary;
import com.star.site.repository.VocabularyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VocabularyService {
    @Resource
    private VocabularyRepository vocabularyRepository;

    public List<Vocabulary> findVocabulariesByDate(String date) {
        return vocabularyRepository.findVocabulariesByDate(date);
    }

    public List<Vocabulary> findAll() {
        return vocabularyRepository.findAll();
    }

    public List<Map<String, Object>> findVocabularyNumGroupByDate() {
        return vocabularyRepository.findVocabularyNumGroupByDate();
    }

    public List<Vocabulary> saveAll(List<Vocabulary> vocabularies) {
        return vocabularyRepository.saveAll(vocabularies);
    }

    public void deleteVocabulariesByDate(String date) {
        vocabularyRepository.deleteVocabulariesByDate(date);
    }
}
