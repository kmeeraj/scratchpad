package com.teradata.appserver.mulitpledb.h2;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface SqleFunctionRepository extends CrudRepository<SqleFunction,Long> {

    public SqleFunction getSqleFunctionByCategoryAndFunctionName(String category, String FunctionName);
    public SqleFunction getSqleFunctionByFunctionNameAndCategory(String FunctionName, String category);
    public List<SqleFunction> getSqleFunctionByFunctionName(String FunctionName);
}
