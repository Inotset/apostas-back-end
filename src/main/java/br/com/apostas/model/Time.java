package br.com.apostas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Time extends GenericEntity {
	
	private String nome;
	
	private byte[] imagem;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "oid")
	private List<Usuario> usuarios;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
