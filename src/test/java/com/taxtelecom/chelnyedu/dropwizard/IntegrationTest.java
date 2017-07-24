package com.taxtelecom.chelnyedu.dropwizard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.assertThat;

import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * Tests:
 * for ContactMapper;
 * serialize/deserialize JSON;
 * for Contact.fetHashCode
 */
public class IntegrationTest {
    final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private Contact contact = new Contact(1, "John", "Doe", "+123456789", "john@mail.ru", "norm");

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
        Contact con = new Contact(1, "a","a","a", "a", "a");
        Contact contact = new Contact(2, "a","a","a", "a", "a");
        assertThat(con.hashCode()).isNotEqualTo(contact.hashCode());
    }
}
