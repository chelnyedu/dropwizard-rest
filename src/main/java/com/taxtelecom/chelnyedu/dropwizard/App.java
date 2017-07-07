package com.taxtelecom.chelnyedu.dropwizard;

import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;

public class App extends Application<PhonebookConfiguration>{
private static final org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);
    @Override
    public void initialize(Bootstrap<PhonebookConfiguration> b) {}
    @Override
    public void run(PhonebookConfiguration c, Environment e) throws
            Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(e, c.getDataSourceFactory(), "postgresql");
        e.jersey().register(new ContactResources(jdbi));
        logger.info("Method App#run() called");
        for (int i=0; i < c.getMessageRepetitions(); i++) {
        	System.out.println(c.getMessage());
        }
    }

    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }

}
