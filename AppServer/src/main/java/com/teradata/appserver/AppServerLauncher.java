package com.teradata.appserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@SpringBootApplication
@EnableTransactionManagement
public class AppServerLauncher {
    private static final Logger LOGGER= LoggerFactory.getLogger(AppServerLauncher.class);
    public static void main(String[] args) throws IOException {

        ConfigurableApplicationContext context = SpringApplication.run(AppServerLauncher.class, args);
        context.getBean(InitTable.class).fillWithTestdata();
    }

}
