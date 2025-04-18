package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * Refresh Scope
     * <a href="https://docs.spring.io/spring-cloud-commons/reference/spring-cloud-commons/application-context-services.html#refresh-scope">doc</a>
     * */
    @RefreshScope
    @Bean
    DataSource dataSource(@Value("${db-url}") String url,
                          @Value("${db-username}") String username,
                          @Value("${db-password}") String password) {

        logger.info("DB url secret loaded: {}", url);
        logger.info("DB username secret loaded: {}", username);
        logger.info("DB password secret loaded: {}", password);

        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}