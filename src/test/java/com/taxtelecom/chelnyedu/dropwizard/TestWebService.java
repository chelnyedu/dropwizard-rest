package com.taxtelecom.chelnyedu.dropwizard;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import com.taxtelecom.chelnyedu.dropwizard.resources.ClientResource;
import com.taxtelecom.chelnyedu.dropwizard.resources.ContactResources;
import io.dropwizard.testing.junit.DropwizardAppRule;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.validation.Validator;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
/**
 * Created by user on 14.07.17.
 */
public class TestWebService {
    ClientResource clientResource = mock(ClientResource.class);

    Client client = mock(Client.class);
    WebResource contactResource = mock(WebResource.class);
    private Contact contactForTest = new Contact
            (0, "Jane", "Doe", "+987654321");
    @ClassRule
    public static final DropwizardAppRule<PhonebookConfiguration> RULE =
            new DropwizardAppRule<PhonebookConfiguration>
                    (App.class, "config.yaml");
    @Before
    public void setUp() {
        client = new Client();
        when(clientResource.showContact(0)).thenReturn(String.valueOf(contactForTest));
    }

    @Test
    public void getContactTest() {
        String s=clientResource.showContact(0);
        assertThat(s).isEqualTo(String.valueOf(contactForTest));
    }
}
