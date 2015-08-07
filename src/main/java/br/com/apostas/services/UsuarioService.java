package br.com.apostas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario save(Usuario usuario) {
		try {
			usuario = getManager().merge(usuario);
			return usuario;
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
}
