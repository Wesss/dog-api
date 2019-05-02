package com.wes-develop.dog-api;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class dog-apiApplication extends Application<dog-apiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new dog-apiApplication().run(args);
    }

    @Override
    public String getName() {
        return "dog-api";
    }

    @Override
    public void initialize(final Bootstrap<dog-apiConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final dog-apiConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
