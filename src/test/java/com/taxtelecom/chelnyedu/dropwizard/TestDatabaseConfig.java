package com.taxtelecom.chelnyedu.dropwizard;

import io.dropwizard.db.DataSourceFactory;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.assertThat;
import io.dropwizard.db.DatabaseConfiguration;

/**
 * Created by user on 12.07.17.
 */

public class TestDatabaseConfig {
    @Test
    public void checkParser(){

        String url = "postgres://me:123456@localhost:5432/contact";
        DatabaseConfiguration dbConfig = DatabaseConfig.create(url);
        DataSourceFactory dsf = dbConfig.getDataSourceFactory(null);
        assertThat(dsf.getDriverClass()).isEqualTo("org.postgresql.Driver");
        assertThat(dsf.getPassword()).isEqualTo("123456");
        assertThat(dsf.getUser()).isEqualTo("me");
        assertThat(dsf.getUrl()).isEqualTo("jdbc:postgresql://localhost:5432/contact");

    }

}
