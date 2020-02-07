package com.star.site;

import com.star.site.utils.StarDateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StarSiteBackendApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(StarDateUtils.getTime());
    }
}
