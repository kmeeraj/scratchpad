package com.teradata.appserver;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:multiple-db-boot.properties"})
@EnableJpaRepositories(
        basePackages = "com.teradata.appserver.mulitpledb",
        entityManagerFactoryRef = "customerEntityManagerFactory",
        transactionManagerRef = "userTransactionManager")
public class AzureConfiguration {


    @Bean(name = "azureDS")
    @ConfigurationProperties(prefix="spring.azure")
    public DataSource azureDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "azureJdbcTemplate")
    public JdbcTemplate jdbcTemplate1(@Qualifier("azureDS") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean customerEntityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(azureDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan("com.teradata.appserver.mulitpledb.azure");

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager userTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                customerEntityManagerFactory().getObject());
        return transactionManager;
    }
}
