package com.taxtelecom.chelnyedu.dropwizard;
import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDao;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by user on 11.07.17.
 */
public class TestContactResources {
    private static final ContactDao dao = mock(ContactDao.class);
    Response response;
    private static final ContactResources resource = new ContactResources(dao);


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new ContactResources(dao)).build();

    private final Contact contact = new Contact(2, "MIR", "RIM", "123456");

    @Before
    public void setup(){
        when(dao.getContactById(eq(2))).thenReturn(contact);
    }

    @After
    public void tearDown(){
        reset(dao);
    }

    @Test
    public void testGetContact(){
        response = resource.getContact(1);
        assertThat(response.getStatus()).isEqualTo(200);

    }
    @Test
    public void testPostContact() throws URISyntaxException{
        response = resource.createContact(contact);
        assertThat(response.getStatus()).isEqualTo(201);

    }

    @Test
    public void testDeleteContact() {
        response = resource.deleteContact(1);
        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void testUpdateContact(){
        response = resource.updateContact(2, contact);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testGetContact2(){
        assertThat(resources.client().resource("http://localhost:8080/contact/2").getRequestBuilder().get(Contact.class)).isEqualTo(contact);
        verify(dao).getContactById(2);
    }
}
