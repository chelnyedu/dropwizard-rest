package com.taxtelecom.chelnyedu.dropwizard;

import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.Configuration;


public class App extends Application<Configuration>{
private static final org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);
    @Override
    public void initialize(Bootstrap<Configuration> b) {}
    @Override
    public void run(Configuration c, Environment e) throws
            Exception {
        logger.info("Method App#run() called");
        System.out.println( "Hello world, by Dropwizard!" );
    }

    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }

}
