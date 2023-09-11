package com.company.config;

import java.util.Map;

public class Configuration {
    private int port;
    private Map<String, String> paths;

    public int getPort() {
        return port;
    }

    public Map<String, String> getPaths() {
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
