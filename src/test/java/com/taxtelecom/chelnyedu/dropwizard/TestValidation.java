package com.taxtelecom.chelnyedu.dropwizard;

import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.net.URISyntaxException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import javax.validation.ValidatorFactory;
import javax.ws.rs.core.Response;

/**
 * Created by user on 13.07.17.
 */
public class TestValidation {
    private Contact contact = new Contact(1, "John", "Doe", "+123456789");
    private Validator validator;
    private ContactDAO dao = mock(ContactDAO.class);


    @Test
    public void validationContact() throws URISyntaxException {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        ContactResources res = new ContactResources(dao, validator);
        Response response = res.createContact(contact);
        assertThat(response.getStatus()).isEqualTo(400);
        response = res.updateContact(1, contact);
        assertThat(response.getStatus()).isEqualTo(400);
    }

}
