package com.teradata.appserver.controller;

import com.teradata.appserver.mulitpledb.h2.FunctionTime;
import com.teradata.appserver.mulitpledb.h2.FunctionTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/time")
public class FunctionalTimeRestController {

    @Autowired
    FunctionTimeRepository functionTimeRepository;

    @GetMapping("/all")
    public Map<String, Graph>  getAllTimeGraph(){

        List<FunctionTime> list = StreamSupport.stream(functionTimeRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
        Map<String, Graph> map = new HashMap<>();
        for(FunctionTime functionTime : list){
            Graph graph = null;

            if(map.containsKey(functionTime.getFunctionName())){
                graph = map.get(functionTime.getFunctionName());
            }else {
                graph = new Graph();
            }
            boolean aws = false;
            boolean azure = false;
            if(functionTime.getCloud().equalsIgnoreCase("AWS")){
                graph.setAws(true);
                graph.setAwsCount(graph.getAwsCount() + 1);
                graph.setAwsvalue(graph.getAwsvalue() + 1);
            } else {
                graph.setAzure(true);
                graph.setAzureCount(graph.getAzureCount() + 1);
                graph.setAzurevalue(graph.getAzurevalue() + 1);
            }
            map.put(functionTime.getFunctionName(), graph);

        }

        for(Map.Entry<String, Graph> entry : map.entrySet()){
            Graph graph = entry.getValue();
            graph.setAzurevalue((graph.getAzurevalue()/graph.getAzureCount()));
            graph.setAwsvalue((graph.getAwsvalue()/graph.getAzureCount()));
        }
        return map;

    }
}
