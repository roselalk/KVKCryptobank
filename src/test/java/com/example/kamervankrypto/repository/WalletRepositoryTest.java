package com.example.kamervankrypto.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class WalletRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(WalletRepositoryTest.class);

    private WalletRepository walletRepository;

    @Autowired
    public WalletRepositoryTest(WalletRepository walletRepository) {
        super();
        this.walletRepository = walletRepository;
        logger.info("New instance of WalletRepositoryTest created.");
    }

    @Test
    public void wallet_repository_is_available() {
        assertThat(walletRepository).isNotNull();
    }



}