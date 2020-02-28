package com.teradata.appserver.controller;


import com.teradata.appserver.AnalyticJSONParser;
import com.teradata.appserver.mulitpledb.h2.Analytical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AnalyticalRestController {

    @Autowired
    private AnalyticJSONParser parser;

    @GetMapping("/analytics")
    public List<Analytical> getAnalytics() throws IOException {
        return parser.parseJson();
    }
}
