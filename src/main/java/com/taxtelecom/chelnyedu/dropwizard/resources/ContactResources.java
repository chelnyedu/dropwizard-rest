package com.taxtelecom.chelnyedu.dropwizard.resources;

import javax.validation.ConstraintViolation;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import com.taxtelecom.chelnyedu.dropwizard.dao.ContactDAO;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;

import io.dropwizard.auth.Auth;

import javax.validation.Validator;


@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResources {

    private final ContactDAO contactDAO;
    private final Validator validator;

    public ContactResources(ContactDAO dao, Validator validator){
        contactDAO = dao;
        this.validator = validator;
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id,
    		@Auth Boolean isAuthenticated){
        Contact contact = contactDAO.getContactById(id);
        return Response.ok(contact).build();
    }

    @POST
    public Response createContact(Contact contact, @Auth Boolean isAuthenticated)
    		throws URISyntaxException{
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        if (!violations.isEmpty()) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Contact> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() +": " + violation.getMessage());
            }
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(validationMessages)
                    .build();
        }
        else {
            int newContactId = contactDAO.createContact(contact.getFirstName(),
                            contact.getLastName(), contact.getPhone(), contact.getMail(), contact.getComment());
            return Response.created(new
                    URI(String.valueOf(newContactId))).build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id, @Auth Boolean isAuthenticated){
        contactDAO.deleteContact(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, Contact contact,
    		@Auth Boolean isAuthenticated){
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);

        if (!violations.isEmpty()) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Contact> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() +": "
                        + violation.getMessage());
            }
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(validationMessages)
                    .build();
        }
        else {
            contactDAO.updateContact(id, contact.getFirstName(),
                    contact.getLastName(), contact.getPhone(), contact.getMail(), contact.getComment());
            return Response.ok(
                    new Contact(id, contact.getFirstName(),
                            contact.getLastName(),
                            contact.getPhone(),
                            contact.getMail(),
                            contact.getComment())).build();
        }

    }

}
