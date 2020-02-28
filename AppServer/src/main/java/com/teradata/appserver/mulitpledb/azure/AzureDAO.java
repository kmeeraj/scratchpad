package com.teradata.appserver.mulitpledb.azure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AzureDAO {

    @Autowired
    @Qualifier("azureJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void executeQuery(String query){
        jdbcTemplate.execute(query);
    }
}
