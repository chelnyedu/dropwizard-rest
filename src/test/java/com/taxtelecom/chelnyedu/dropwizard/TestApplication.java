package com.taxtelecom.chelnyedu.dropwizard;

import static org.fest.assertions.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.fest.assertions.api.Assertions.assertThat;


import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;

/**
 * Created by sagel on 06.07.17.
 */

public class TestApplication {
    private Contact emptyContact = new Contact();
    private Contact contact = new Contact(0, null, null, null);
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void checkEmpty(){
        assertThat(emptyContact.getFirstName()).isEqualTo(contact.getFirstName());
        assertThat(emptyContact.getLastName()).isEqualTo(contact.getLastName());
        assertThat(emptyContact.getPhone()).isEqualTo(contact.getPhone());
        assertThat(emptyContact.getId()).isEqualTo(contact.getId());
    }

    @Test
    public void serializesToJson() throws Exception{
        final Contact contact1 = new Contact(1, "John", "Doe", "+987654321");
        assertThat(MAPPER.writeValueAsString(contact1)).isEqualTo(fixture("fixtures/contact.json"));
    }

    @Test
    public void deserializesFromJson() throws Exception{
        final Contact contact2 = new Contact(1, "John", "Doe", "+987654321");
        assertThat(MAPPER.readValue(fixture("fixtures/contact.json"),
                Contact.class)).isEqualTo(contact2);
    }
}
