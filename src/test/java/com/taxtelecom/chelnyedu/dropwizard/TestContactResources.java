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
    private static final ContactResources resource = mock(ContactResources.class);


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
        when(resource.getContact(1)).thenReturn(Response.ok(contact).build());
        response = resource.getContact(1);
        assertThat(response.getStatus()).isEqualTo(200);

    }
    @Test
    public void testPostContact() throws URISyntaxException{
        when(resource.createContact(contact)).thenReturn(Response.ok(contact).build());
        response = resource.createContact(contact);
        assertThat(response.getStatus()).isEqualTo(200);

    }

    @Test
    public void testDeleteContact() {
        when(resource.deleteContact(1)).thenReturn(Response.ok(contact).build());
        response = resource.deleteContact(1);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testUpdateContact(){
        when(resource.updateContact(2, contact)).thenReturn(Response.ok(contact).build());
        response = resource.updateContact(2, contact);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void testGetContact2(){

        assertThat(resources.client().resource("http://localhost:8080/contact/2").getRequestBuilder().get(Contact.class)).isEqualTo(contact);
        verify(dao).getContactById(2);
    }



    @Test
    public void testPostContact2(){
        assertThat(resources.client().resource("http://localhost:8080/contact/2").
                getRequestBuilder().post(Contact.class)).isEqualTo(contact);

        verify(dao).createContact("MIR", "RIM", "123456");
    }

    @Test
    public void testDeleteContact2(){
        assertThat(resources.client().resource("http://localhost:8080/contact/2").getRequestBuilder().delete(Contact.class)).isEqualTo(contact);
        verify(dao).deleteContact(2);
    }



}
