package com.taxtelecom.chelnyedu.dropwizard;

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
import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
/**
 * Created by sagel on 06.07.17.
 */
public class TestApplication {
	private Client client;
	private static ContactDAO dao = mock(ContactDAO.class);
    private Contact contactForTest = new Contact(0, "John", "Doe", "+123456789");
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ContactResources(dao)).build();
    @Before
    public void setup() {
        when(dao.getContactById(0)).thenReturn(contactForTest);
        reset(dao);
    }

    /*@Test
    public void testGetPerson() {
        assertThat(resources.client().resource("http://localhost:8080/contact/0").getRequestBuilder()
        		.get(Contact.class)).isEqualTo(contactForTest);
        verify(dao).getContactById(0);
    }
    @ClassRule
    public static final DropwizardAppRule<PhonebookConfiguration> RULE =
            new DropwizardAppRule<PhonebookConfiguration>(App.class, "config.yaml");

    @Test
    public void checkResponse(){
    	WebResource contactResource =
    			client.resource("http://localhost:8080/contact");
        ClientResponse response = contactResource.type(MediaType.APPLICATION_JSON)
        		.post(ClientResponse.class, contactForTest);
        assertThat(response.getStatus()).isEqualTo(201);
    }*/
    
    @Test
    public void checkContact(){
        ContactResources resources = mock(ContactResources.class);
        
        when(dao.getContactById(0)).thenReturn(contactForTest);
        when(resources.getContact(0)).thenReturn(Response.ok(contactForTest).build());
        Response resp = resources.getContact(0);
        Contact con = (Contact) resp.getEntity();
        
        assertThat(contactForTest.getFirstName()).isEqualTo(con.getFirstName());
        assertThat(contactForTest.getLastName()).isEqualTo(con.getLastName());
        assertThat(contactForTest.getPhone()).isEqualTo(con.getPhone());
        assertThat(contactForTest.getId()).isEqualTo(con.getId());
    }
    @Test
    public void checkConfig()
    {
    	PhonebookConfiguration config = new PhonebookConfiguration();
    	DataSourceFactory dsf = config.getDataSourceFactory();
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
