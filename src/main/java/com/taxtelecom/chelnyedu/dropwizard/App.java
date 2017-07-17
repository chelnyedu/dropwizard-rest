package com.taxtelecom.chelnyedu.dropwizard;

import com.google.common.cache.CacheBuilderSpec;
import com.sun.jersey.api.client.Client;
import com.taxtelecom.chelnyedu.dropwizard.PhonebookAuthenticator.PhonebookAuthenticator;
import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.resources.ClientResources;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
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
        e.jersey().register(new ClientResources(client));
        CachingAuthenticator<BasicCredentials, Boolean> authenticator = new CachingAuthenticator<BasicCredentials, Boolean>(
                        e.metrics(), new PhonebookAuthenticator(jdbi),
                        CacheBuilderSpec.parse("maximumSize=10000, expireAfterAccess=10m"));
        e.jersey().register(new BasicAuthProvider<Boolean>(authenticator, "Web Service Realm"));
        //e.jersey().register(new BasicAuthProvider<Boolean>(new PhonebookAuthenticator(jdbi), "Web Service Realm"));
    }

    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }

}
