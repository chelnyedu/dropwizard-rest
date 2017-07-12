package com.taxtelecom.chelnyedu.dropwizard;

import io.dropwizard.db.DataSourceFactory;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import io.dropwizard.testing.junit.ResourceTestRule;

import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
