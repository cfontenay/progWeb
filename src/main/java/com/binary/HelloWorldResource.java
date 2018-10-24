package com.binary;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.stream.Collectors;

@Path("/home")
public class HelloWorldRessource {
    @GET
    @Produces("text/plain")
    public String getUsers() {
        StringBuffer buf = new StringBuffer();
        for(User u : UsersManager.getInstance().getAll())
            buf.append((new UserRessource()).getJSON(u));
        return buf.toString();
    }
    
    @POST
    public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        User u = new User();
        u.firstName = firstName;
        u.lastName = lastName;
        UsersManager.getInstance().add(u);
        return Response.status(200).entity("Add ok").build();
    }
    
    @DELETE
    public Response deleteUser(@PathParam("id") long id) {
        if (UsersManager.getInstance().remove(id))
            return Response.status(200).entity("Delete ok").build();
        else 
            return Response.status(401).entity("Delete refused").build();
    }
    @PUT
    public Response setUser(@PathParam("id") long id) {
        //TODO : et par la même occasion tester le pathParam (pour Delete et put) mais pour 
        //ça il faut d'abord le html
        return null;
    }
}
