package com.star.site.repository;

import com.star.site.entity.Views;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ViewsRepository extends JpaRepository<Views, Long> {

    Views findFirstByIpOrderByIdDesc(String ip);

    @Query(value = "select count(id) from views", nativeQuery = true)
    Long countTotal();

    @Query(value = "select province as name, count(id) as value from star_blog.views group by province", nativeQuery = true)
    List<Map<String, Object>> countProvince();

    @Query(value = "select city as name, count(id) as value from star_blog.views where province like ?1 group by city", nativeQuery = true)
    List<Map<String, Object>> countCity(String province);

    @Query(value = "select date, count(id) as num from views group by date order by date desc limit 7", nativeQuery = true)
    List<Map<String, Object>> countLast7DaysViews();
}
