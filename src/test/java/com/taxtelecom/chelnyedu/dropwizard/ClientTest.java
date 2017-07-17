package com.taxtelecom.chelnyedu.dropwizard;

import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ClientResource;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import static org.fest.assertions.api.Assertions.assertThat;

public class ClientTest {
    ClientResource clientRes = mock(ClientResource.class);
    Contact contact = new Contact(1,"Alex","Brown","12456789");
    Response response;
    String output = "ID: "+ contact.getId()
            +"\nFirst name: " + contact.getFirstName()
            + "\nLast name: " + contact.getLastName()
            + "\nPhone: " + contact.getPhone();

    @Before
    public void setup() {
        when(clientRes.newContact(contact.getFirstName(),contact.getLastName(),contact.getPhone()))
                .thenReturn(Response.status(302).build());
        when(clientRes.updateContact(1,contact.getFirstName(),contact.getLastName(),contact.getPhone()))
                .thenReturn(Response.status(302).build());
        when(clientRes.deleteContact(1)).thenReturn(Response.noContent().build());
        when(clientRes.showContact(1)).thenReturn(output);
    }

    @Test
    public void createContactTest(){
        response = clientRes.newContact("Alex","Brown","12456789");
        assertThat(response.getStatus()).isEqualTo(302);
    }

    @Test
    public void updateContactTest(){
        response = clientRes.updateContact(1,contact.getFirstName(),contact.getLastName(),contact.getPhone());
        assertThat(response.getStatus()).isEqualTo(302);
    }

    @Test
    public void showContactTest(){
        String show = clientRes.showContact(1);
        assertEquals(show,output);
    }

    @Test
    public void deleteContactTest(){
        response = clientRes.deleteContact(1);
        assertThat(response.getStatus()).isEqualTo(204);
    }
}
