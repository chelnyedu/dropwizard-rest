package com.taxtelecom.chelnyedu.dropwizard;

import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class TestApplication {

    private static final ContactDAO dao = mock(ContactDAO.class);
    @ClassRule
    public static final ResourceTestRule res = ResourceTestRule.builder()
            .addResource(new ContactResources(dao)).build();

    private ContactResources resources = new ContactResources(dao);
    private Contact contact = new Contact(1, "John", "Doe", "+123456789");
    private Response response;


    @Test
    public void envStringTest() {

       String url = "postgres://me:123456@localhost:5432/contact";
        DatabaseConfiguration dbConfig = DataBaseConfiguration.create(url);
        DataSourceFactory dsf = dbConfig.getDataSourceFactory(null);
        assertThat(dsf.getDriverClass()).isEqualTo("org.postgresql.Driver");
        assertThat(dsf.getUser()).isEqualTo("me");
        assertThat(dsf.getPassword()).isEqualTo("123456");
        assertThat(dsf.getUrl()).isEqualTo("jdbc:postgresql://localhost:5432/contact");
    }

    @Test
    public void getContactStatusTest() {
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
    public void checkEmptyContactTest(){
        Contact checkContact = new Contact();
        Contact empty = new Contact(0,null, null, null);
        assertThat(checkContact.getId()).isEqualTo(empty.getId());
        assertThat(checkContact.getFirstName()).isEqualTo(empty.getFirstName());
        assertThat(checkContact.getLastName()).isEqualTo(empty.getLastName());
        assertThat(checkContact.getPhone()).isEqualTo(empty.getPhone());
    }
}