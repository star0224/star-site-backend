package com.star.site;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class StarSiteBackendApplicationTests {


    @Test
    void contextLoads() throws CloneNotSupportedException {
        UUID.randomUUID();
    }
}
