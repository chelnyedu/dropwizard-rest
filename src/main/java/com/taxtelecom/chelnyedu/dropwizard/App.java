package com.taxtelecom.chelnyedu.dropwizard;

import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;


public class App extends Application<PhonebookConfiguration>{
private static final org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);
    @Override
    public void initialize(Bootstrap<PhonebookConfiguration> b) {}
    @Override
    public void run(PhonebookConfiguration c, Environment e) throws Exception {
        logger.info("Method App#run() called");
        System.out.println(c.getAdditionalMessage());
        for (int i = 0; i < c.getMessageRepetitions(); i++){
            System.out.println(c.getMessage());
        }

    }

    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }

}
