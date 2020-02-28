package com.teradata.appserver.mulitpledb.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AwsDAO {

    @Autowired
    @Qualifier("awsJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void executeQuery(String query){
        jdbcTemplate.execute(query);
    }
}
