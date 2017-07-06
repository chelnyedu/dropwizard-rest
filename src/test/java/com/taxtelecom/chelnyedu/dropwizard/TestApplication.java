package com.taxtelecom.chelnyedu.dropwizard;

import static org.fest.assertions.api.Assertions.assertThat;
import javax.ws.rs.core.Response;
import org.junit.Test;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
/**
 * Created by sagel on 06.07.17.
 */
public class TestApplication {
    private Contact contactForTest = new Contact(0, "John", "Doe", "+123456789");

    @Test
    public void checkContact(){
        ContactResources resources = new ContactResources();
        Response contact = resources.getContact(0);
        Contact con = (Contact) contact.getEntity();

        assertThat(contactForTest.getFirstName()).isEqualTo(con.getFirstName());
        assertThat(contactForTest.getLastName()).isEqualTo(con.getLastName());
        assertThat(contactForTest.getPhone()).isEqualTo(con.getPhone());

    }
}
