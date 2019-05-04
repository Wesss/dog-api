package com.wesdevelop.dogapi;

import com.wesdevelop.dogapi.resources.DogResource;
import io.dropwizard.Configuration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DogApiApplication extends Application<Configuration> {

    public static void main(final String[] args) throws Exception {
        new DogApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "DogApi";
    }

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
    }

    @Override
    public void run(final Configuration configuration,
                    final Environment environment) {
        final DogResource resource = new DogResource();
        environment.jersey().register(resource);
    }

}
