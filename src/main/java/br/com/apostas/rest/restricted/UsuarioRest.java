package br.com.apostas.rest.restricted;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.apostas.dto.UsuarioDTO;
import br.com.apostas.misc.JsonConverter;
import br.com.apostas.services.UsuarioService;


@Path("/usuarios")
public class UsuarioRest {
	
	@Inject
	private UsuarioService usuarioService; 

	@GET
	@Produces("application/json")
	public Response getTodosUsuarios() {
		List<UsuarioDTO> usuarios = new ArrayList<>();

		usuarios = usuarioService.buscarTodosUsuariosDto();

		if (usuarios.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Não há usuários cadastrados!").build();
		} else {
			return Response.ok(JsonConverter.toJson(usuarios)).build();
		}
	}

}
