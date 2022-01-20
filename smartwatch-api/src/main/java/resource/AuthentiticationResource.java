package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.JWTHelper;
import entity.Credentials;

@Path("auth")
public class AuthentiticationResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response auth(Credentials credentials) {
		return Response.ok(JWTHelper.generateToken(credentials)).build();
	}

}
