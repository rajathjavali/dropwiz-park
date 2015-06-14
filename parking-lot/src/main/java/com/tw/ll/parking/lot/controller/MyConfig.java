package com.tw.ll.parking.lot.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.annotation.Nonnegative;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyConfig extends Configuration {

    Properties prop=new Properties();
    String configFileName="config.properties";

    public MyConfig() throws IOException {
        maxSlots = getMaxSlotsFromConfig();
    }

    private int getMaxSlotsFromConfig() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
        prop.load(inputStream);
        return Integer.parseInt(prop.getProperty("max"));
    }

    @Nonnegative
    private int maxSlots;

    @JsonProperty
    public int getMaxSlots() {
        return maxSlots;
    }

    @JsonProperty
    public void setMaxSlots(int max) {
        maxSlots = max;
    }

}
