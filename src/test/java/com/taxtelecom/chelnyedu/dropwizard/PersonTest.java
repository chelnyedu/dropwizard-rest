package com.taxtelecom.chelnyedu.dropwizard;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.fest.assertions.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;

public class PersonTest {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    final Contact contact = new Contact(1,"John","Doe", "0123456789");
    @Test
    public void serializesToJSON() throws Exception {
        assertThat(MAPPER.writeValueAsString(contact))
                .isEqualTo(fixture("com/taxtelecom/chelnyedu/dropwizard/resources/fixtures/person.json"));
    }
    @Test
    public void deserializesFromJSON() throws Exception {
    	assertThat(MAPPER.readValue(fixture("com/taxtelecom/chelnyedu/dropwizard/resources/fixtures/person.json"),
    			Contact.class)).isEqualTo(contact);
    }
}
