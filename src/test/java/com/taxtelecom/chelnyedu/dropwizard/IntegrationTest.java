package com.taxtelecom.chelnyedu.dropwizard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.net.URISyntaxException;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.Response;

import static io.dropwizard.testing.FixtureHelpers.fixture;


public class IntegrationTest {
    final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private Contact contact = new Contact(1, "John", "Doe", "+123456789");

    @Test
    public void serializesToJSON() throws Exception {
        assertThat(MAPPER.writeValueAsString(contact)).isEqualTo(fixture("person.json"));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat(MAPPER.readValue(fixture("person.json"), Contact.class)).isEqualTo(contact);
    }
    
    @Test
    public void getHashCodeTest() {
        Contact con = new Contact(1, "a","a","a");
        Contact contact = new Contact(2, "a","a","a");
        assertThat(con.hashCode()).isNotEqualTo(contact.hashCode());
    }
    @Test
    public void badRequestTest() throws URISyntaxException {
    	contact = new Contact(1,"1","1","1");
    	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
    	ContactDAO dao = mock(ContactDAO.class);
        ContactResources resources = new ContactResources(dao, validator);
    	Response response = resources.createContact(contact);
        assertThat(response.getStatus()).isEqualTo(400);
        response = resources.updateContact(1, contact);
        assertThat(response.getStatus()).isEqualTo(400);
    }
}
