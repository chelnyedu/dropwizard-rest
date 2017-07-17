package com.taxtelecom.chelnyedu.dropwizard;

import com.sun.jersey.api.client.Client;
import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.resources.ClientResource;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;

public class App extends Application<PhonebookConfiguration>{
private static final org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);
    @Override
    public void initialize(Bootstrap<PhonebookConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<PhonebookConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(PhonebookConfiguration config){
                return config.getDataSourceFactory();
            }
        });
    }
    @Override
    public void run(PhonebookConfiguration c, Environment e) throws Exception {

        logger.info("Method App#run() called");
        for (int i=0; i < c.getMessageRepetitions(); i++) {
        	System.out.println(c.getMessage());
        }

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(e,c.getDataSourceFactory(), "myPostgres");
        e.jersey().register(new ContactResources(jdbi.onDemand(ContactDAO.class), e.getValidator()));

        final Client client = new JerseyClientBuilder(e).build("REST Client");
        e.jersey().register(new ClientResource(client));
    }

    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }

}
