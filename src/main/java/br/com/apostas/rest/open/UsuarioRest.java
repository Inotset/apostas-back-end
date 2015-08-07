package br.com.apostas.rest.open;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.apostas.dto.UsuarioDTO;
import br.com.apostas.misc.JsonConverter;
import br.com.apostas.model.Usuario;
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
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response salvarUsuario(String jsonUsuario){
		
		Usuario usuario = JsonConverter.fromJson(jsonUsuario, Usuario.class);
		
		usuario = usuarioService.save(usuario);
		
		return Response.status(Response.Status.CREATED).entity(usuario).build();
	}
}
