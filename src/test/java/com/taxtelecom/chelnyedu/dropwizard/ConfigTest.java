package com.taxtelecom.chelnyedu.dropwizard;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ConfigTest {

    @Test
    public void configTest() {
        DatabaseConfiguration dbConfig = DataBaseConfiguration.create("postgres://me:123456@localhost:5432/contact");
        DataSourceFactory dsf = dbConfig.getDataSourceFactory(null);
        assertThat(dsf.getDriverClass()).isEqualTo("org.postgresql.Driver");
        assertThat(dsf.getUser()).isEqualTo("me");
        assertThat(dsf.getPassword()).isEqualTo("123456");
        assertThat(dsf.getUrl()).isEqualTo("jdbc:postgresql://localhost:5432/contact");
    }
}
