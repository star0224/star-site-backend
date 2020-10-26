package com.star.site.repository;

import com.star.site.entity.BackSchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BackSchoolRepository extends JpaRepository<BackSchoolInfo, Long> {
    BackSchoolInfo findBackSchoolInfoByStudentId(String studentId);

    @Query(value = "select distinct province from back_school_info order by province asc" , nativeQuery = true)
    List<String> findDistinctProvince();

    @Query(value = "select distinct city from back_school_info order by city asc" , nativeQuery = true)
    List<String> findDistinctCity();

    @Query(value = "select distinct district from back_school_info order by district asc" , nativeQuery = true)
    List<String> findDistinctDistrict();

    @Query(value = "select distinct clazz from back_school_info order by clazz asc" , nativeQuery = true)
    List<String> findDistinctClazz();
}
