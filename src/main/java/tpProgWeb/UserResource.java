package tpProgWeb;



import org.bson.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("/")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Response getUsers() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (Document doc : MongoManager.getInstance().getAll()){
            buffer.append(doc.toJson());
            buffer.append(",");
        }
        buffer.setCharAt(buffer.length()-1, ']');
        
        return Response.ok().entity(buffer.toString()).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{id}")
    public Response getUser(@PathParam("id") String id){
    	String s = "["+MongoManager.getInstance().getUser(id).toJson() + "]";
    	return Response.ok().entity(s).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("user/add")
    public Response addUser(User user) {
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("user/{id}")
    public Response setUser(@PathParam("id") String id, User infos ){
        if (!id.equals("")){
            MongoManager.getInstance().setUser(id, infos.firstName, infos.lastName);
            return Response.ok().build();

        }
        return Response.status(Response.Status.BAD_REQUEST).build();

    }
}
