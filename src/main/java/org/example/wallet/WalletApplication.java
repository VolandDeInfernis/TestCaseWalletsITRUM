package org.example.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableRetry // Активируем механизм повторных попыток
@EnableTransactionManagement // Явное включение управления транзакциями
public class WalletApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);
    }
}