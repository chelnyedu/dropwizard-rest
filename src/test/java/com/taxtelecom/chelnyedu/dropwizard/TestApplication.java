package com.taxtelecom.chelnyedu.dropwizard;

import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestApplication {

    private static final ContactDAO dao = mock(ContactDAO.class);
    private ContactResources resources = new ContactResources(dao, validator);
    private Contact contact = new Contact(1, "John", "Doe", "+123456789", "john@mail.ru", "norm");
    private Response response;

    private static Validator validator = mock(Validator.class);

    @Before
    public void setup() {
        when(dao.getContactById(0)).thenReturn(contact);
        doNothing().when(dao).deleteContact(0);
        doNothing().when(dao).updateContact(contact.getId(), contact.getFirstName(),
                contact.getLastName(), contact.getPhone(), contact.getMail(), contact.getComment());
        when(dao.createContact(contact.getFirstName(), contact.getLastName(),
                contact.getPhone(), contact.getMail(), contact.getComment())).thenReturn(1);
    }

    @After
    public void resetDAO() {
        reset(dao);
    }

    @Test
    public void getContactTest() {
        response = resources.getContact(1);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void createContactTest() throws URISyntaxException{
        response = resources.createContact(contact);
        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void deleteContactTest(){
        response = resources.deleteContact(1);
        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void updateContactTest(){
        response = resources.updateContact(1, contact);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void getMessageTest(){
        String message = null;
        String optionalMessage = "This is optional";
        PhonebookConfiguration config = new PhonebookConfiguration();
        assertThat(config.getMessage()).isEqualTo(message);
        assertThat(config.getAdditionalMessage()).isEqualTo(optionalMessage);
    }

    @Test
    public void getAllContactTest(){
        response = resources.getAllContact();
        assertThat(response.getStatus()).isEqualTo(200);
    }
}