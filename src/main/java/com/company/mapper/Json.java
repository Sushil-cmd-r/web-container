package com.company.mapper;

import com.company.config.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json implements Mapper<Configuration> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public Configuration parse(String src) throws JsonProcessingException {
        return mapper.readValue(src, Configuration.class);
    }
}
