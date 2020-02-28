package com.teradata.appserver;

import com.teradata.appserver.mulitpledb.h2.Analytical;
import com.teradata.appserver.mulitpledb.h2.Functions;
import com.teradata.appserver.mulitpledb.h2.SqleFunction;
import com.teradata.appserver.mulitpledb.h2.SqleFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitTable {

    @Autowired
    SqleFunctionRepository sqleFunctionRepository;

    @Autowired
    AnalyticJSONParser analyticJSONParser;

    public void fillWithTestdata() throws IOException {
        List<Analytical> list = analyticJSONParser.parseJson();
        List<SqleFunction> functions = new ArrayList<>();
        for(Analytical analytical : list){
            for(Functions functions1 : analytical.getFunctions()){
                functions.add(SqleFunction.builder().category(analytical.getCategory()).functionName(functions1.getFunctionName()).build());
            }
        }
        sqleFunctionRepository.saveAll(functions);
    }
}
