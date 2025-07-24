package org.example.wallet.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/master.xml");
        liquibase.setContexts("dev,prod");
        liquibase.setShouldRun(true);
        return liquibase;
    }
}