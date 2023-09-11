package com.company.config;

import com.company.mapper.Json;
import com.company.mapper.Mapper;
import com.company.mapper.Properties;
import com.company.mapper.Xml;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigManager {
    private static ConfigManager configManger;
    private final ConfigType type;

    private final Mapper<Configuration> mapper;
    private final String[] exnts = {".properties", ".json", ".xml"};

    private ConfigManager(ConfigType type) {
        this.type = type;
        if(this.type == ConfigType.JSON)
            this.mapper = new Json();
        else if(this.type == ConfigType.XML)
            this.mapper = new Xml();
        else
            this.mapper = new Properties();
    }

    public static ConfigManager getInstance(ConfigType type) {
        if(configManger == null) {
            configManger = new ConfigManager(type);
        }
        return configManger;
    }

    public Configuration loadConfig() {
        try(InputStream input = getClass().
                getClassLoader().getResourceAsStream("config" + exnts[type.ordinal()]);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input))){
            StringBuilder buffer = new StringBuilder();
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                buffer.append(line);
                line = reader.readLine();
            }

            System.out.println(buffer.toString());
            return mapper.parse(buffer.toString());


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
