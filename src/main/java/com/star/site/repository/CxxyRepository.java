package com.star.site.repository;


import com.star.site.entity.Cxxy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CxxyRepository extends JpaRepository<Cxxy, Integer> {
    Cxxy findCxxyByStudentIdAndName(String studentId, String name);
}
