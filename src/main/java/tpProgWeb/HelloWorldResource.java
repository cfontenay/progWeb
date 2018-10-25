package tpProgWeb;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("/user")
public class HelloWorldResource {
    @GET
    @Produces("text/plain")
    @Path("/all")
    public String getUsers() {
        StringBuffer buffer = new StringBuffer();
        for (Document doc : MongoManager.getInstance().getAll()){
            buffer.append(doc.toJson());
        }
        return buffer.toString();
    }
    
    @POST
    @Path("/add")
    public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        User user = new User();
        user.firstName = firstName;
        user.lastName = lastName;
        if(!MongoManager.getInstance().addUser(user))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.ok().build();

    }
    
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") String id) {
    	
        if (!MongoManager.getInstance().deleteUser(id))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.ok().build();
    }
    @PUT
    @Path("{id}")
    public Response setUser(@PathParam("id") String id,
                            @FormParam("firstName") String firstName,
                            @FormParam("lastName") String lastName) throws IOException {
        if (!id.equals("")){
            MongoManager.getInstance().setUser(id, firstName, lastName);
            return Response.ok().build();

        }
        return Response.status(Response.Status.BAD_REQUEST).entity("ID required").build();

    }
}
