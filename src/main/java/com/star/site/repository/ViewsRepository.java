package com.star.site.repository;

import com.star.site.entity.Views;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewsRepository extends JpaRepository<Views, Long> {

    Views findFirstByIpOrderByIdDesc(String ip);
}
