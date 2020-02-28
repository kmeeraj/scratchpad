package com.teradata.appserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:multiple-db-boot.properties"})
@EnableJpaRepositories(
        basePackages = "com.teradata.mulitpledb")
public class H2PrimaryDBConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.h2")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }

}
