package com.oaxaca.customer_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(classes = CustomerServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class CustomerServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
