package com.taxtelecom.chelnyedu.dropwizard.resources;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ClientResources;
import com.sun.jersey.api.client.*;

public class ClientTest {
	private static final WebResource webResourse = mock(WebResource.class);
    private static final ContactDAO dao = mock(ContactDAO.class);
    private static final Client client = mock(Client.class);
    private ClientResources resources = new ClientResources(client);
    private Contact contact = new Contact(0, "John", "Doe", "+123456789");
    private Response response;


    @Before
    public void setup() throws URISyntaxException {
        when(dao.getContactById(0)).thenReturn(contact);
        doNothing().when(dao).deleteContact(0);
        doNothing().when(dao).updateContact(contact.getId(), contact.getFirstName(),
                contact.getLastName(), contact.getPhone());
        when(dao.createContact(contact.getFirstName(), contact.getLastName(),
                contact.getPhone())).thenReturn(1);
        when(client.resource(Mockito.anyString())).thenReturn(webResourse);
        when(webResourse.get(Contact.class)).thenReturn(contact);
    }
    @After
    public void resetDAO() {
        reset(dao);
    }
    @Test
    public void showContactTest() {
    	resources.contactResource = webResourse;
		String output = "ID: " + contact.getId()
				+ "\nFirst name: " + contact.getFirstName()
				+ "\nLast name: " + contact.getLastName()
				+ "\nPhone: " + contact.getPhone();
    	String outputTest = resources.showContact(0);
    	assertThat(output).isEqualTo(outputTest);
    }
    @Test
    public void deleteContactTest() {
    	resources.contactResource = webResourse;
    	response = resources.deleteContact(0);
    	assertThat(response.getStatus()).isEqualTo(204);
    }
}
