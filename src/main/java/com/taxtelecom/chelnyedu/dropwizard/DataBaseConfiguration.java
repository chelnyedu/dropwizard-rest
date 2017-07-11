package com.taxtelecom.chelnyedu.dropwizard;


import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;


public class DataBaseConfiguration implements DatabaseConfiguration {
    static final Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);
    private static DatabaseConfiguration dbConfiguration;

    public static DatabaseConfiguration create(){
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            final String user = dbUri.getUserInfo().split(":")[0];
            final String pass = dbUri.getUserInfo().split(":")[1];
            final String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            dbConfiguration = new DataBaseConfiguration() {
                DataSourceFactory dataSourceFactory;

                @Override
                public DataSourceFactory getDataSourceFactory(Configuration configuration) {
                    DataSourceFactory factory = new DataSourceFactory();
                    factory.setUser(user);
                    factory.setPassword(pass);
                    factory.setUrl(url);
                    factory.setDriverClass("org.postgresql.Driver");
                    dataSourceFactory = factory;
                    return factory;
                }
            };
        }catch (URISyntaxException u){
            logger.info(u.getMessage());
        }

        return dbConfiguration;
    }

    @Override
    public DataSourceFactory getDataSourceFactory(Configuration configuration){
        return dbConfiguration.getDataSourceFactory(null);
    }
}
