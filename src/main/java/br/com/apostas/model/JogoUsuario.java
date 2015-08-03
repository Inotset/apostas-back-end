package br.com.apostas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "jogo_usuario")
public class JogoUsuario extends GenericEntity {
	
	@Column(name = "oid_jogo")
	private Jogo jogo;
	
	@Column(name = "oid_usuario")
	private Usuario usuario;

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
