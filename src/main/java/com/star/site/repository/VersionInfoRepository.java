package com.star.site.repository;

import com.star.site.entity.VersionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface VersionInfoRepository extends JpaRepository<VersionInfo, Integer> {
    @Modifying
    @Transactional
    @Query(value = "truncate table version_info", nativeQuery = true)
    public void truncateTable();
}
