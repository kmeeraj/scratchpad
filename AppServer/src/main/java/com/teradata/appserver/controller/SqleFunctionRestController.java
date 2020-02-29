package com.teradata.appserver.controller;

import com.teradata.appserver.AnalyticJSONParser;
import com.teradata.appserver.mulitpledb.RequestDTO;
import com.teradata.appserver.mulitpledb.Response;
import com.teradata.appserver.mulitpledb.aws.AwsDAO;
import com.teradata.appserver.mulitpledb.azure.AzureDAO;
import com.teradata.appserver.mulitpledb.h2.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/run")
public class SqleFunctionRestController {

    private static final Logger LOGGER= LoggerFactory.getLogger(SqleFunctionRestController.class);

    @Autowired
    SqleFunctionRepository sqleFunctionRepository;

    @Autowired
    private AnalyticJSONParser parser;

    @Autowired
    private AwsDAO awsDAO;

    @Autowired
    private AzureDAO azureDAO;

    @Autowired
    private FunctionTimeRepository functionTimeRepository;

    @GetMapping("/functions")
    public List<SqleFunction> functions(){
        List<SqleFunction>  list =
                StreamSupport.stream(sqleFunctionRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());
        return list;
    }

    @PostMapping("/runAll")
    public Response runAll(@RequestBody String message) throws IOException {
        List<Analytical> list = parser.parseJson();
        List<FunctionTime> timeReport = new ArrayList<>();
        for(Analytical analytical: list){
            for(Functions funct: analytical.getFunctions()){
                executeOnCloud(true, true, timeReport, funct);
            }
        }
        saveAllFunctionTime(timeReport);
        return Response.builder().status("success").message("successfully submitted.").build();
    }

    @PostMapping("/runAnalytics")
    public Response runAnalytics(@RequestBody RequestDTO requestDTO) throws IOException {
        LOGGER.info("Request: "+requestDTO);
        boolean enableAWS = false;
        boolean enableAzure = false;
        for (String cloud : requestDTO.getCloud()){
            switch (cloud){
                case "AWS":
                    enableAWS = true;
                    break;
                case "Azure":
                    enableAzure = true;
                    break;
            }
        }
        List<FunctionTime> timeReport = new ArrayList<>();
        List<Analytical> list = parser.parseJson();
        Analytical functions = list.stream().filter(analytical -> analytical.getCategory().equals(requestDTO.getCategory())).findFirst().orElse(null);
        Functions funct = functions.getFunctions().stream().filter(function -> function.getFunctionName().equals(requestDTO.getFunction())).findFirst().orElse(null);

        executeOnCloud(enableAWS, enableAzure, timeReport, funct);
        saveAllFunctionTime(timeReport);
        return Response.builder().status("success").message("successfully submitted.").build();
    }

    private void saveAllFunctionTime(List<FunctionTime> timeReport) {
        try {
            functionTimeRepository.saveAll(timeReport);
        } catch (DataAccessException e){
            LOGGER.error("could not execute", e);
        }
    }

    private void executeOnCloud(boolean enableAWS, boolean enableAzure, List<FunctionTime> timeReport, Functions funct) {
        for(String query: funct.getQuery()){

            if(enableAWS){
                long startTime = System.currentTimeMillis();
                int errorCode = 0;
                String errorMessage = "";
                try {
                    awsDAO.executeQuery(query);
                } catch (DataAccessException e){
                    errorMessage = e.getMessage();
                }catch (Exception e){
                    errorMessage = e.getMessage();
                }
                finally {
                    if(errorMessage.length() > 200)
                        errorMessage = errorMessage.substring(0,200);
                    long endTime = System.currentTimeMillis();
                    FunctionTime functionTime = FunctionTime.builder()
                           // .sqleFunction(sqleFunction)
                            .cloud("AWS")
                            .query(query)
                            .errorMessage(errorMessage)
                            .functionName(funct.getFunctionName())
                            .timeTakenMs(endTime - startTime)
                            .build();
                    timeReport.add(functionTime);


                }
            }

            if(enableAzure) {
                long startTime = System.currentTimeMillis();
                int errorCode = 0;
                String errorMessage = "";
                try{
                    azureDAO.executeQuery(query);
                } catch (DataAccessException e){
                    errorMessage = e.getMessage();
                }catch (Exception e){
                    errorMessage = e.getMessage();
                }
                finally {
                    long endTime = System.currentTimeMillis();
                    if(errorMessage.length() > 200)
                      errorMessage = errorMessage.substring(0,200);
                    FunctionTime functionTime = FunctionTime.builder()
                           // .sqleFunction(sqleFunction)
                            .cloud("Azure")
                            .query(query)
                            .errorMessage(errorMessage)
                            .functionName(funct.getFunctionName())
                            .timeTakenMs(endTime - startTime)
                            .build();
                    timeReport.add(functionTime);

                }
            }
        }
    }

}
