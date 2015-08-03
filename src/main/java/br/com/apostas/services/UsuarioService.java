package br.com.apostas.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;

import br.com.apostas.dto.UsuarioDTO;
import br.com.apostas.model.Usuario;
import br.com.apostas.repositories.UsuarioRepository;

@TransactionManagement
@Stateless
public class UsuarioService extends GenericService {
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioDTO> buscarTodosUsuariosDto(){
		List<UsuarioDTO> usuariosDto = new ArrayList<>();
		
		List<Usuario> usuarios = usuarioRepository.getTodosUsuarios();
		
		if (!usuarios.isEmpty()){
			for (Usuario usuario : usuarios) {
				
				UsuarioDTO userDto = new UsuarioDTO();
				userDto.admin = usuario.getAdmin();
				userDto.bloqueado = usuario.getBloqueado();
				userDto.criacao = usuario.getCriacao();
				userDto.email = usuario.getEmail();
				userDto.nome = usuario.getNome();
				
				usuariosDto.add(userDto);
			}
		}
		
		return usuariosDto;
	}
}
