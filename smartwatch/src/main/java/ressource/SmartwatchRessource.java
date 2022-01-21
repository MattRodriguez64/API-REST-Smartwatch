package ressource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import smartwatchdata.SmartwatchData;
import java.lang.Math;  

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@Path("datas")
@Tag(name = "SmartwatchData")
public class SmartwatchRessource {

	private List<SmartwatchData> datas = new ArrayList<>();
	private boolean done = false;

	public SmartwatchRessource() {
		
		if(!done) {
			for(int i =  0; i < 100; i++) {
				int pas = (int) Math.floor(Math.random()*(20000-0+1)+0);
				int userId = (int) Math.floor(Math.random()*(5-1+1)+1);
				double tension = Math.random()*(20d-10d+1)+10d;
				double rythmeCardiaque = Math.random()*(200d-60d+1)+60d;
				int randomHeure = (int) Math.floor(Math.random()*(10-4+1)+4);
				int randomMinute = (int) Math.floor(Math.random()*(59-0+1)+0);
				System.out.println(randomHeure);
				String sommeil = (String) (randomHeure + "h" + randomMinute + "min");
				int calorie = (int) Math.floor(Math.random()*(10000-0+1)+0);
				
				SmartwatchData data = new SmartwatchData(i, userId, pas, tension, rythmeCardiaque, sommeil, calorie);
				datas.add(data);
			}
			done = true;
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get all Datas", description = "Get all Datas", responses = {
			@ApiResponse(responseCode = "200", description = "Succes", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SmartwatchData.class)))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response getAllData() {
		return Response.ok(datas).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Get a SmartwatchData statement", description = "Get a SmartwatchData statement by its id", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SmartwatchData.class))),
			@ApiResponse(responseCode = "404", description = "SmartwatchData statement not found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response getSmartwatchData(@PathParam("id") int id) {
		return Response.ok(datas.stream().filter(
				data -> id == data.getId())
				.findAny().orElse(null)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Add a SmartwatchData", description = "Add a SmartwatchData", responses = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response addSmartwatchData(SmartwatchData data) {
		datas.add(data);
		return Response.ok(datas).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Update an SmartwatchData statement", description = "Update an SmartwatchData statement by its id", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SmartwatchData.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response update(@PathParam("id") int id, SmartwatchData newData) {
		SmartwatchData oldData = datas.stream().filter(data -> id == data.getId()).findAny().orElse(null);
		if (oldData == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		datas.remove(id);
		datas.add(newData);
		return Response.ok(datas).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Delete a SmartwatchData statement", description = "Delete a SmartwatchData statement by its id", responses = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response delete(@PathParam("id") int id) {
		datas.remove(id);
		return Response.ok(datas).build();
	}
	

	@GET
	@Path("_search")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Search all the SmartwatchData statement belonging to a user", description = "Search all the SmartwatchData statement belonging to a user, using its userId", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SmartwatchData.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@SecurityRequirement(name = "bearer-auth")
	public Response search(@QueryParam("userId") Integer userId) {
		List<SmartwatchData> userDatas = new ArrayList<>();
		SmartwatchData currentData;
		if(userId != null) {
			for(int i = 0; i < datas.stream().count(); i++) {
				currentData = datas.stream().filter(data -> userId == data.getUserId()).findAny().orElse(null);
				if(currentData != null){
					userDatas.add(currentData);
				}
			}
			return Response.ok(userDatas).build();
		}
		else {
			return Response.ok(datas).build();
		}
	}
}




