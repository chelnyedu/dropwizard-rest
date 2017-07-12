package com.taxtelecom.chelnyedu.dropwizard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.assertThat;

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
}
