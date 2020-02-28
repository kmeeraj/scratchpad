package com.teradata.appserver.mulitpledb.h2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class Analytical {

    private String category;
    private List<Functions> functions;

}
