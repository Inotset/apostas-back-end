package br.com.apostas.rest.open;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import br.com.apostas.dto.Credentials;
import br.com.apostas.dto.Message;
import br.com.apostas.model.Usuario;
import br.com.apostas.repositories.UsuarioRepository;
import br.com.apostas.security.Sessao;
import br.com.apostas.security.Sessoes;

@Path("/login")
public class LoginRest {

	@Inject
	private UsuarioRepository usuarioRepo;
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(Credentials credentials, @Context HttpServletRequest req) {

		Usuario usuario = null;
		
		try {
			usuario = usuarioRepo.findByEmailandSenha(credentials.getEmail(), credentials.getSenha());
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(new Message("Login ou senha invalidos!").toJson()).build();
		}
		
		if (usuario.getPassword().equals(credentials.getSenha())) {

			if (usuario.getInativo()) {
				return Response.status(Response.Status.UNAUTHORIZED)
						.entity(new Message("O Usuário está inativo e não pode acessar o sistema!").toJson()).build();
			}

			Sessoes sessoes = Sessoes.getInstance();
			Sessao sessao = new Sessao(usuario, req.getRemoteAddr());
			sessoes.getHashSessoes().put(sessao.getOid(), sessao);
			
			NewCookie userId = new NewCookie("userId", sessao.getUsuario().getOid());
			NewCookie userName = new NewCookie("userName", sessao.getUsuario().getNome());
			NewCookie sessionId = new NewCookie("sessionId", sessao.getOid());

			Credentials credentialsRetorno = new Credentials();
			credentialsRetorno.setSessionId(sessao.getOid());
			credentialsRetorno.setEmail(sessao.getUsuario().getEmail());
			credentialsRetorno.setNome(sessao.getUsuario().getNome());
			credentialsRetorno.setAdmin(sessao.getUsuario().getAdmin());

			return Response.ok().cookie(sessionId, userId, userName).entity(credentialsRetorno.toJson()).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(new Message("Login e senha invalidos!").toJson()).build();
		}
		
	}
}
