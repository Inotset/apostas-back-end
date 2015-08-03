package br.com.apostas.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Rodada extends GenericEntity {
	
	private Integer numero;
	
	@Column(name = "oid_usuario_vencedor")
	private Usuario usuario;
	
	@Column(name = "oid_torneio")
	private Torneio torneio;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

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

}
