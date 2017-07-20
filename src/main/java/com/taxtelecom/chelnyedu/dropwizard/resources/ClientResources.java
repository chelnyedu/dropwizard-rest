package com.taxtelecom.chelnyedu.dropwizard.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.sun.jersey.api.client.*;
import com.taxtelecom.chelnyedu.dropwizard.representations.Contact;
import java.util.List;

@Produces(MediaType.TEXT_PLAIN)
@Path("/client/")
public class ClientResources {
	private ClientResponse response;
	private WebResource contactResource;
	private Client client;
	public ClientResources(Client client) {
		this.client = client;
	}
	String url = "http://localhost:8080/contact/";
	public void setWebResource(WebResource contactResource) {
		this.contactResource = contactResource;
	}

	@GET
	@Path("showContact")
	public String showContact(@QueryParam("id") int id) {

		contactResource = client.resource(url + id);
		Contact c = contactResource.get(Contact.class);
		return "ID: " + id
				+ "\nFirst name: " + c.getFirstName()
				+ "\nLast name: " + c.getLastName()
				+ "\nPhone: " + c.getPhone()
				+ "\nMail: " + c.getMail()
				+ "\nComment " + c.getComment();
	}
	@GET
	@Path("newContact")
	public Response newContact(
			@QueryParam("firstName")String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("phone") String phone,
			@QueryParam("mail") String mail,
			@QueryParam("comment") String comment) {
		contactResource = client.resource(url);
		response = contactResource
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, new Contact(0, firstName, lastName, phone, mail, comment));
		if (response.getStatus() == 201) {
			return Response.status(302).entity("The contact was created successfully! "
					+ "The new contact can be found at " +
					response.getHeaders().getFirst("Location")).build();
		} else {
			return Response.status(422).entity(response.
					getEntity(String.class)).build();
		}
	}
	@GET
	@Path("updateContact")
	public Response updateContact(@QueryParam("id") int id,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("phone") String phone,
			@QueryParam("mail") String mail,
			@QueryParam("comment") String comment) {
		contactResource = client.resource(url + id);
		response = contactResource.type(MediaType.
				APPLICATION_JSON).put(ClientResponse.class, new Contact(id,
						firstName, lastName, phone, mail, comment));
		if (response.getStatus() == 200) {
			return Response.status(302).entity("The contact was updated successfully!").build();
	} else {
		return Response.status(422).entity(response.
				getEntity(String.class)).build();
		}
	}

	@GET
	@Path("deleteContact")
	public Response deleteContact(@QueryParam("id") int id) {
		contactResource = client.resource(url + id);
		contactResource.delete();
		return Response.noContent().entity("Contact was deleted!").build();
	}

	@GET
    @Path("showAllContact")
    @Produces(MediaType.APPLICATION_JSON)
    public List showAllContact() {
        contactResource = client.resource(url + "all");
        return contactResource.get(List.class);

    }
}
