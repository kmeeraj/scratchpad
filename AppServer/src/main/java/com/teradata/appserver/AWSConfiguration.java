package com.teradata.appserver;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:multiple-db-boot.properties"})
@EnableJpaRepositories(
        basePackages = "com.teradata.mulitpledb")
public class AWSConfiguration {

    @Bean
    @ConfigurationProperties(prefix="spring.aws")
    public DataSource awsDataSource() {
        return DataSourceBuilder.create().build();
    }

}
