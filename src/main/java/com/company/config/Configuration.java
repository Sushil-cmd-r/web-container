package com.company.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JacksonXmlRootElement(localName = "Configuration")
public class Configuration {
    private int port;
    private List<Path> paths = new ArrayList<>();

    public int getPort() {
        return port;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "port=" + port +
                ", paths=" + paths +
                '}';
    }
}

class Path {
    private String endpoint;
    private String servlet;

    public String getEndpoint() {
        return endpoint;
    }

    public String getServlet() {
        return servlet;
    }

    @Override
    public String toString() {
        return "Path{" +
                "endpoint='" + endpoint + '\'' +
                ", servlet='" + servlet + '\'' +
                '}';
    }
}



