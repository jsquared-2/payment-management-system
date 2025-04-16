package com.pms.api.payment.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Default application test.
 * <p>
 * Enabling in-memory database to ensure passing test.
 */
@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "spring.datasource.driver-class-name=org.h2.Driver", "spring.datasource.username=sa", "spring.datasource.password=", "spring.jpa.hibernate.ddl-auto=create-drop", "spring.jpa.show-sql=true"})
class PaymentServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
