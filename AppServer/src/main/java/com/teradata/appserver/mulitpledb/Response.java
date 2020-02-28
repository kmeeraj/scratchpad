package com.teradata.appserver.mulitpledb;

import lombok.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Response {

    private String status;
    private String message;
    private String[] errormessage;
}
