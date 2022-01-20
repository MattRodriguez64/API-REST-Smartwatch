package auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jwt.JwtHelper;
import customer.Credentials;

@Path("auth")
public class Auth {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response auth(Credentials credentials) {
		return Response.ok(JwtHelper.generateToken(credentials)).build();
	}

}