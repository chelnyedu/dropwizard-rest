package com.taxtelecom.chelnyedu.dropwizard;

import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;

public class ConfigTest {
    @Test
    public void checkConfig()
    {
    	DatabaseConfiguration config = DatabaseConfig.create("postgres://me:123456@localhost:5432/contact");
    	DataSourceFactory dsf = config.getDataSourceFactory(null);
    	assertThat(dsf.getDriverClass()).isEqualTo("org.postgresql.Driver");
    	assertThat(dsf.getUser()).isEqualTo("me");
    	assertThat(dsf.getPassword()).isEqualTo("123456");
    	assertThat(dsf.getUrl()).isEqualTo("jdbc:postgresql://localhost:5432/contact");
    }
    @Test(expected = IllegalStateException.class)
    public void checkException() {
    	DatabaseConfig databaseConfiguration = new DatabaseConfig();
    	databaseConfiguration.getDataSourceFactory(null);
    }
}
