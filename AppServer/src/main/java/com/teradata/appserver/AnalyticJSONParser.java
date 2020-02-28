package com.teradata.appserver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.teradata.appserver.mulitpledb.h2.Analytical;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class AnalyticJSONParser {
    private static final Logger LOGGER= LoggerFactory.getLogger(AnalyticJSONParser.class);

    public static void main(String[] args) throws IOException {
        AnalyticJSONParser parser= new AnalyticJSONParser();
        parser.parseJson();
    }

    public List<Analytical> parseJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        TypeFactory typeFactory = mapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(
                List.class, Analytical.class);
        Resource resource = new ClassPathResource("analytic.json");
        InputStream input = resource.getInputStream();
        List<Analytical> analytical = mapper.readValue(input, collectionType);
        LOGGER.info(analytical.toString());
        return analytical;
    }
}
