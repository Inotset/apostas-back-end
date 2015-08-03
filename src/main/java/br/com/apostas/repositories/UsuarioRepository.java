package br.com.apostas.repositories;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

import br.com.apostas.model.Usuario;

@Named
@RequestScoped
public class UsuarioRepository extends GenericRepository<Usuario> {

	public UsuarioRepository() {
		super(Usuario.class);
	}

	public Usuario findByEmailandSenha(String email, String senha) {
		List<Usuario> usuarios = getManager().createQuery("SELECT u FROM Usuario u "
				+ " WHERE u.email = :email AND u.password = :senha")
				.setParameter("email", email)
				.setParameter("senha", senha)
				.getResultList();

		if (usuarios != null && usuarios.size() == 1) {
			return usuarios.get(0);
		} else {
			throw new EntityNotFoundException("Usuário não encontrado");
		}
	}
	
	public List<Usuario> getTodosUsuarios(){
		return getManager().createQuery("SELECT u FROM Usuario u ")
				.getResultList();
	}
}
