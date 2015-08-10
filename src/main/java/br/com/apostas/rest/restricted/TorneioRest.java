package br.com.apostas.rest.restricted;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.apostas.dto.TorneioDTO;
import br.com.apostas.misc.JsonConverter;
import br.com.apostas.model.Torneio;
import br.com.apostas.services.TorneioService;

@Path("/torneios")
public class TorneioRest {
	
	@Inject
	private TorneioService torneioServices;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response salvarTorneio(String jsonTorneio){
		
		Torneio torneio = JsonConverter.fromJson(jsonTorneio, Torneio.class);
		torneio = torneioServices.save(torneio);
		
		return Response.status(Response.Status.CREATED)
				.entity(JsonConverter.toJson(torneio)).build();
		
	}
	
	@GET
	@Produces("application/json")
	public Response getTodosTorneios() {
		List<TorneioDTO> torneios = new ArrayList<>();

		torneios = torneioServices.buscarTodosTorneiosDto();

		if (torneios.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Não há torneios cadastrados!").build();
		} else {
			return Response.ok(JsonConverter.toJson(torneios)).build();
		}
	}
	
	@DELETE
	@Path("/{oid}")
	public Response deletarTime(@PathParam("oid") String oid){
		try{
			torneioServices.delete(oid);
			return Response.status(Response.Status.CREATED).entity("Torneio excluido com sucesso!").build();
		} catch (Exception erro) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao excluir torneio!").build();
		}
	}

}
