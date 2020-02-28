package com.teradata.appserver.mulitpledb.h2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Functions {
    private String functionName;
    private String[] query;
}
