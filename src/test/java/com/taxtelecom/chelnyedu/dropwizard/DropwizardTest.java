package com.taxtelecom.chelnyedu.dropwizard;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;
import com.taxtelecom.chelnyedu.dropwizard.representation.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import org.junit.ClassRule;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.assertThat;
import io.dropwizard.testing.junit.DropwizardAppRule;

public class DropwizardTest {

    private Contact contactForTest = new Contact(0, "Jane", "Doe", "+98754321");
    private Client client = new Client();
    private WebResource contactResource = client.resource("http://localhost:8080/contact");

    @ClassRule
    public static final DropwizardAppRule<PhonebookConfiguration> RULE =
            new DropwizardAppRule<PhonebookConfiguration>(App.class, "config.yaml");

    @Test
    public void responseTest(){
        ClientResponse response = contactResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, contactForTest);
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void retrieveContactTest(){
        ContactResources resource = new ContactResources();
        Response responce = resource.getContact(0);
        Contact contact = (Contact)responce.getEntity();
        assertThat(contact.getFirstName()).isEqualTo(contactForTest.getFirstName());
        assertThat(contact.getLastName()).isEqualTo(contactForTest.getLastName());
        assertThat(contact.getPhone()).isEqualTo(contactForTest.getPhone());
    }
}
