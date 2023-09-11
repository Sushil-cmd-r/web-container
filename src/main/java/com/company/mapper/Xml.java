package com.company.mapper;

import com.company.config.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Xml implements Mapper<Configuration> {

    ObjectMapper mapper = new XmlMapper();
    @Override
    public Configuration parse(String src) throws JsonProcessingException {
        return mapper.readValue(src, Configuration.class);
    }
}
