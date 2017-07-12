package com.taxtelecom.chelnyedu.dropwizard.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;

import java.net.URI;
import java.net.URISyntaxException;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResources {

    private final ContactDAO contactDAO;

    public ContactResources(ContactDAO dao){
        contactDAO = dao;
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id){
        Contact contact = contactDAO.getContactById(id);
        return Response.ok(contact).build();
    }

    @POST
    public Response createContact(Contact contact) throws URISyntaxException{
        int newContactId = contactDAO.createContact(contact.getFirstName(),contact.getLastName(),contact.getPhone());
        return  Response.created(new URI(String.valueOf(newContactId))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id){
        contactDAO.deleteContact(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, Contact contact){
       contactDAO.updateContact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.ok(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone())).build();
    }

}
