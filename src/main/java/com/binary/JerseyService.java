package com.binary;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


@Path("/user")
public class JerseyService {
    @GET
    @Produces("text/plain")
    @Path("/all")
    public String getUsers() {
        StringBuffer buf = new StringBuffer();
        for(User u : UsersManager.getInstance().getAll())
            buf.append((new UserRessource()).getJSON(u));
        return buf.toString();
    }
    
    @POST
    @Path("/add")
    public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        User u = new User();
        u.firstName = firstName;
        u.lastName = lastName;
        UsersManager.getInstance().add(u);
        return Response.status(200).entity("Add ok").build();
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") String id) {
    	
        if (UsersManager.getInstance().remove(Long.parseLong(id)))
            return Response.status(200).entity("Delete ok").build();
        else 
            return Response.status(401).entity("Delete refused").build();
    }
    @PUT
    @Path("{id}")
    public Response setUser(@PathParam("id") String id) {
        //TODO : et par la même occasion tester le pathParam (pour Delete et put) mais pour 
        //ça il faut d'abord le html
        return null;
    }
}