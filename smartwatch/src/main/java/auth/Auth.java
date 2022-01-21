package auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jwt.JwtHelper;
import smartwatchdata.SmartwatchData;
import customer.Credentials;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@Path("auth")
@Tag(name = "Authentication")
public class Auth {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Operation(summary = "Authentication with credentials", description = "Authentication with credentials, to return JWT token, needed for each request.", responses = {
			@ApiResponse(responseCode = "200", description = "Succes", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SmartwatchData.class)))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response auth(Credentials credentials) {
		return Response.ok(JwtHelper.generateToken(credentials)).build();
	}

}