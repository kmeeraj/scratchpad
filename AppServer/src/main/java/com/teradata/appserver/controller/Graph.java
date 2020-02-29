package com.teradata.appserver.controller;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class Graph {

    private boolean aws;
    private boolean azure;
    private long awsvalue;
    private long azurevalue;
    private int awsCount = 0;
    private int azureCount = 0;

}
