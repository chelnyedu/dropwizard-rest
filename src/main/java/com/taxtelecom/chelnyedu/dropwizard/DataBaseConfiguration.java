package com.taxtelecom.chelnyedu.dropwizard;


import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataBaseConfiguration implements DatabaseConfiguration {
    static final Logger logger = LoggerFactory.getLogger(DataBaseConfiguration.class);
    private static DatabaseConfiguration dbConfiguration;

    public static DatabaseConfiguration create(String url) {
        String adress = url.split("@")[1];
        final String user = url.split("@")[0].split(":")[1].substring(2);
        final String pass = url.split("@")[0].split(":")[2];
        final String href = "jdbc:postgresql://" + adress;
                dbConfiguration = new DataBaseConfiguration() {
            DataSourceFactory dataSourceFactory;
                @Override
                public DataSourceFactory getDataSourceFactory(Configuration configuration){
                    DataSourceFactory factory = new DataSourceFactory();
                    factory.setUser(user);
                    factory.setPassword(pass);
                    factory.setUrl(href);
                    factory.setDriverClass("org.postgresql.Driver");
                    dataSourceFactory = factory;
                    return factory;
                }
            };

        return dbConfiguration;
    }

    @Override
    public DataSourceFactory getDataSourceFactory(Configuration configuration){
        return dbConfiguration.getDataSourceFactory(null);
    }
}
