package com.star.site.repository;

import com.star.site.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Integer> {
    List<Vocabulary> findVocabulariesByDate(String date);

    @Transactional
    void deleteVocabulariesByDate(String date);

    @Query(value = "select count(id) as vocabularyNum, date from vocabulary group by date order by date asc", nativeQuery = true)
    List<Map<String, Object>> findVocabularyNumGroupByDate();

}
