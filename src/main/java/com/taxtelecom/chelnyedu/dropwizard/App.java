package com.taxtelecom.chelnyedu.dropwizard;

import org.slf4j.LoggerFactory;

import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;

public class App extends Application<ApplicationConfiguration>{
private static final org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);
    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> b) {}
    @Override
    public void run(ApplicationConfiguration c, Environment e) throws Exception {
    	e.jersey().register(new ContactResource());
    }

    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }

}
