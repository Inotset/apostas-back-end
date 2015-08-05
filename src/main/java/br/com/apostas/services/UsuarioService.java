package br.com.apostas.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;

import br.com.apostas.dto.UsuarioDTO;
import br.com.apostas.model.Time;
import br.com.apostas.model.Usuario;
import br.com.apostas.repositories.TimeRepository;
import br.com.apostas.repositories.UsuarioRepository;

@TransactionManagement
@Stateless
public class UsuarioService extends GenericService {
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	private TimeRepository timeRepository;
	
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
				
				if (usuario.getTime() != null){
					Time time = timeRepository.findByOid(usuario.getTime().getOid());
					userDto.imagemTime = time.getImagem();
				}
				
				 
				usuariosDto.add(userDto);
			}
		}
		
		return usuariosDto;
	}
}
