package com.example.kamervankrypto.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TraderServiceTest {

    private TraderService traderService;

    @Autowired
    public TraderServiceTest(TraderService service) {
        super();
        traderService = service;
    }

    @Test
    public void testServiceAvailable() {
        assertNotNull(traderService);
    }

    @Test
    void get_all_not_null() {

    }

    @Test
    void get_all_returns_correct_data() {

    }

    @Test
    void getById() {
    }

    @Test
    void getByName() {
    }

    @Test
    void save() {
    }

    @Test
    void upate() {
    }

    @Test
    void delete() {
    }
}