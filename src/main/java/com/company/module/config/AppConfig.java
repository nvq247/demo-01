package com.company.module.config;



import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.company.module.repo")
@EntityScan("com.company.module.model")
public class AppConfig {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AppConfig.class);

}