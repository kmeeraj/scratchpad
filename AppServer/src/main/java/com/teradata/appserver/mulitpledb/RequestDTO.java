package com.teradata.appserver.mulitpledb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class RequestDTO {
    private String category;
    private String function;
    private String query;
    private String [] cloud;
}
