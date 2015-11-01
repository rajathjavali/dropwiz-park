package com.tw.ll.parking.lot.controller;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<MyConfig> {

    @Override
    public void initialize(Bootstrap<MyConfig> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(MyConfig myConfig, Environment environment) throws Exception {
        environment.jersey().register(new ParkingLotController(myConfig.getMaxSlots()));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
