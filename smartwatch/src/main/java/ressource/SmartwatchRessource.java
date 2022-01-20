package ressource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


@Path("datas")
public class SmartwatchRessource {

	private List<SmartwatchData> datas = new ArrayList<>();
	private boolean done = false;

	public SmartwatchRessource() {
		
		if(!done) {
			for(int i =  0; i < 100; i++) {
				int pas = (int) Math.floor(Math.random()*(20000-0+1)+0);
				double tension = Math.random()*(20d-10d+1)+10d;
				double rythmeCardiaque = Math.random()*(200d-60d+1)+60d;
				int randomHeure = (int) Math.floor(Math.random()*(10-4+1)+4);
				int randomMinute = (int) Math.floor(Math.random()*(59-0+1)+0);
				System.out.println(randomHeure);
				String sommeil = (String) (randomHeure + "h" + randomMinute + "min");
				int calorie = (int) Math.floor(Math.random()*(10000-0+1)+0);
				
				SmartwatchData data = new SmartwatchData(i, pas, tension, rythmeCardiaque, sommeil, calorie);
				datas.add(data);
			}
			done = true;
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllData() {
		return Response.ok(datas).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSmartwatchData(@PathParam("id") int id) {
		return Response.ok(datas.stream().filter(
				data -> id == data.getId())
				.findAny().orElse(null)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSmartwatchData(SmartwatchData data) {
		datas.add(data);
		return Response.ok(datas).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
	public Response delete(@PathParam("id") int id) {
		datas.remove(id);
		return Response.ok(datas).build();
	}
}
