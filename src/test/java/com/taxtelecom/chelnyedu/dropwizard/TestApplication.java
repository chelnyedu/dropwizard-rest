package com.taxtelecom.chelnyedu.dropwizard;

import static org.fest.assertions.api.Assertions.assertThat;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.ClassRule;
import org.junit.Test;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import com.taxtelecom.chelnyedu.dropwizard.PhonebookConfiguration;
/**
 * Created by sagel on 06.07.17.
 */
public class TestApplication {
    private Contact contactForTest = new Contact(0, "John", "Doe", "+123456789");
    private Contact emptyContact = new Contact();
    private Contact contact = new Contact(0, null, null, null);
    private Client client = new Client();
    private WebResource contactResource = client.resource("http://localhost:8080/contact");

    @ClassRule
    public static final DropwizardAppRule<PhonebookConfiguration> RULE =
            new DropwizardAppRule<PhonebookConfiguration>(App.class, "config.yaml");

   /* @Test
    public void checkResponse(){
        ClientResponse response = contactResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, contactForTest);
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void checkContact(){
        ContactResources resources = new ContactResources();
        Response contact = resources.getContact(0);
        Contact con = (Contact) contact.getEntity();

        assertThat(contactForTest.getFirstName()).isEqualTo(con.getFirstName());
        assertThat(contactForTest.getLastName()).isEqualTo(con.getLastName());
        assertThat(contactForTest.getPhone()).isEqualTo(con.getPhone());
        assertThat(contactForTest.getId()).isEqualTo(con.getId());
    }

    @Test
    public void checkEmpty(){
        assertThat(emptyContact.getFirstName()).isEqualTo(contact.getFirstName());
        assertThat(emptyContact.getLastName()).isEqualTo(contact.getLastName());
        assertThat(emptyContact.getPhone()).isEqualTo(contact.getPhone());
        assertThat(emptyContact.getId()).isEqualTo(contact.getId());
    }*/
}
