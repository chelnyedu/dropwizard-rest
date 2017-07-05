package com.taxtelecom.chelnyedu.dropwizard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
/**
 * Created by sagel on 04.07.17.
 */
public class App extends Application<Configuration>{
    public static void main(String[] args) throws Exception{
        new App().run(args);
    }
    private static final Logger LOGGER=LoggerFactory.getLogger(App.class);
    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {

    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        LOGGER.info("Method App#run() called");
        System.out.println("Hello world, by Dropwizard!");

    }
}
