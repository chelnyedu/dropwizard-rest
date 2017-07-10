package com.taxtelecom.chelnyedu.dropwizard;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
/**
 * Created by user on 10.07.17.
 */
public class DatabaseConfig implements DatabaseConfiguration {
    private static DatabaseConfiguration databaseConfiguration;

    public static DatabaseConfiguration create() {
        final String login=System.getenv("LOGIN");
        final String password=System.getenv("PASSWORD");
        final String url=System.getenv("URL");
        final String driver=System.getenv("DRIVER");
        databaseConfiguration=new DatabaseConfiguration(){
            DataSourceFactory dataSourceFactory;
            @Override
            public DataSourceFactory getDataSourceFactory(Configuration configuration){
                DataSourceFactory dsf = new DataSourceFactory();
                dsf.setUser(login);
                dsf.setPassword(password);
                dsf.setUrl(url);
                dsf.setDriverClass(driver);
                dataSourceFactory=dsf;
                return  dsf;
            }
        };
        return databaseConfiguration;
    }

    @Override
    public DataSourceFactory getDataSourceFactory(Configuration configuration) {
        if (databaseConfiguration == null) {
            throw new IllegalStateException("You must first call DatabaseConfiguration.create(dbUrl)");
        }
        return databaseConfiguration.getDataSourceFactory(null);
    }
}
