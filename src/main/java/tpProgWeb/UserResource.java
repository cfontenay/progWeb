package tpProgWeb;



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


@Path("/")
public class UserResource {
    @GET
    @Produces("text/plain")
    @Path("/users")
    public Response getUsers() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (Document doc : MongoManager.getInstance().getAll()){
            buffer.append(doc.toJson());
            buffer.append(",");
        }
        buffer.append("]");
        return Response.ok().entity(buffer.toString()).build();
    }
    @GET
    @Produces("text/plain")
	@Path("/user/{id}")
    public Response getUser(@PathParam("id") String id){
    	String s = "["+MongoManager.getInstance().getUser(id).toJson() + "]";
    	return Response.ok().entity(s).build();
    }
    
    @POST
    @Path("user/add")
    public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
        User user = new User();
        user.firstName = firstName;
        user.lastName = lastName;
        if(!MongoManager.getInstance().addUser(user))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.ok().build();
    }
    
    @DELETE
    @Path("user/{id}")
    public Response deleteUser(@PathParam("id") String id) {
    	
        if (!MongoManager.getInstance().deleteUser(id))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.ok().build();
    }
    @PUT
    @Path("user/{id}")
    public Response setUser(@PathParam("id") String id,
                            @FormParam("firstName") String firstName,
                            @FormParam("lastName") String lastName){
        if (!id.equals("")){
            MongoManager.getInstance().setUser(id, firstName, lastName);
            return Response.ok().build();

        }
        return Response.status(Response.Status.BAD_REQUEST).build();

    }
}
