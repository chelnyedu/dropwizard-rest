package com.taxtelecom.chelnyedu.dropwizard.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.skife.jdbi.v2.DBI;

import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
/**
 * Created by sagel on 05.07.17.
 */
@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResources {
    private final ContactDAO contactDao;
    
    public ContactResources(DBI jdbi) {
    contactDao = jdbi.onDemand(ContactDAO.class);
    }
    
	@GET
	@Path("/{id}")
	public Response getContact(@PathParam("id") int id) {
		Contact contact = contactDao.getContactById(id);
		return Response.ok(contact).build();
	}

    @POST
    public Response createContact(Contact contact) throws URISyntaxException{
        int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id){
    	contactDao.deleteContact(id);
    	return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, Contact contact){
    	contactDao.updateContact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone());
    	return Response.ok(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone())).build();
    }
}
