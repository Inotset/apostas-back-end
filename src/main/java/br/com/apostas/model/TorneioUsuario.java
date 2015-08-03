package br.com.apostas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "torneio_usuario")
public class TorneioUsuario extends GenericEntity {
	
	@Column(name = "oid_usuario")
	private Usuario usuario;
	
	@Column(name = "oid_torneio")
	private Torneio torneio;
	
	private Boolean bloqueado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Torneio getTorneio() {
		return torneio;
	}

	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

}
