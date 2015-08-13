package br.com.apostas.rest.restricted;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.apostas.dto.AdicionarPartidaDTO;
import br.com.apostas.misc.JsonConverter;
import br.com.apostas.services.RodadaService;

@Path("/rodadas")
public class RodadaRest {
	
	@Inject
	private RodadaService rodadaService;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response salvarPartidaRodada(String jsonPartidaRodada){
		
		AdicionarPartidaDTO partidaDto = JsonConverter.fromJson(jsonPartidaRodada, AdicionarPartidaDTO.class);
		partidaDto = rodadaService.save(partidaDto);
		
		return Response.status(Response.Status.CREATED).entity(JsonConverter.toJson(partidaDto)).build();
		
	}
	
	@GET
	@Produces("application/json")
	@Path("/{oid}")
	public Response getDadosRodada(@PathParam("oid") String oidRodada){
		AdicionarPartidaDTO partidaDto = rodadaService.getDadosRodada(oidRodada);
		return Response.ok(JsonConverter.toJson(partidaDto)).build();
	}

}
