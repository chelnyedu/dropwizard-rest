package com.taxtelecom.chelnyedu.dropwizard.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
/**
 * Created by sagel on 05.07.17.
 */
@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResources {
    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id){
        return Response.ok( new Contact( id, "John", "Doe", "+123456789")).build();
    }

    @POST
    public Response createContact(Contact contact){
        return  Response.created(null).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id){
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, Contact contact){
        return Response.ok(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone())).build();
    }
}
