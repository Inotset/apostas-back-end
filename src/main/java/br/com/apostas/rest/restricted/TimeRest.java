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

import br.com.apostas.dto.TimeDTO;
import br.com.apostas.misc.JsonConverter;
import br.com.apostas.model.Time;
import br.com.apostas.services.TimeService;

@Path("/times")
public class TimeRest {
	
	@Inject
	private TimeService timeService;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response salvarTime(String jsonTime){
		
		Time time = JsonConverter.fromJson(jsonTime, Time.class);
		
		//time.setImagem(IOUtils.toByteArray(time.getImagem()));
		time = timeService.save(time);
		
		return Response.status(Response.Status.CREATED)
				.entity(JsonConverter.toJson(time)).build();
		
	}
	
	@GET
	@Produces("application/json")
	public Response getTodosTimes() {
		List<TimeDTO> times = new ArrayList<>();

		times = timeService.buscarTodosTimesDto();

		if (times.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Não há times cadastrados!").build();
		} else {
			return Response.ok(JsonConverter.toJson(times)).build();
		}
	}

}
