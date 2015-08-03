package br.com.apostas.rest.restricted;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

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
	public Response salvarTorneio(String jsonTorneio, @QueryParam("imagem") String imagem){
		
		Torneio torneio = JsonConverter.fromJson(jsonTorneio, Torneio.class);
		
		try {
			torneio.setImagem(IOUtils.toByteArray(imagem));
			torneio = torneioServices.save(torneio);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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

}
