package com.taxtelecom.chelnyedu.dropwizard;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
/**
 * Created by sagel on 06.07.17.
 */
public class ApplicationTest {
	private Response response;
	private static final ContactDAO dao = mock(ContactDAO.class);
	private final Contact contactForTest = new Contact(0, "John", "Doe", "+123456789");
	private static final ContactResources resources = new ContactResources(dao);
	@Before
	public void setup(){
		when(dao.getContactById(eq(2))).thenReturn(contactForTest);
		doNothing().when(dao).deleteContact(0);
		doNothing().when(dao).updateContact(contactForTest.getId(), contactForTest.getFirstName(),
				contactForTest.getLastName(), contactForTest.getPhone());
		when(dao.createContact(contactForTest.getFirstName(), contactForTest.getLastName(),
				contactForTest.getPhone())).thenReturn(1);
	}
	@After
	public void resetDAO(){
		reset(dao);
	}
	@Test
	public void responseTest() throws URISyntaxException {
		 response = resources.getContact(1);
		 assertThat(response.getStatus()).isEqualTo(200);
		 response = resources.createContact(contactForTest);
	     assertThat(response.getStatus()).isEqualTo(201);
	     response = resources.deleteContact(1);
	     assertThat(response.getStatus()).isEqualTo(204);
	     response = resources.updateContact(1, contactForTest);
	     assertThat(response.getStatus()).isEqualTo(200);
	}
    @Test
    public void checkContact(){    	
        when(dao.getContactById(0)).thenReturn(contactForTest);
        Response resp = resources.getContact(0);
        Contact con = (Contact) resp.getEntity();

        assertThat(contactForTest.getFirstName()).isEqualTo(con.getFirstName());
        assertThat(contactForTest.getLastName()).isEqualTo(con.getLastName());
        assertThat(contactForTest.getPhone()).isEqualTo(con.getPhone());
        assertThat(contactForTest.getId()).isEqualTo(con.getId());
    }
}
