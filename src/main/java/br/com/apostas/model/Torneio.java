package br.com.apostas.model;

import javax.persistence.Entity;

@Entity
public class Torneio extends GenericEntity {
	
	private String nome;
	
	private byte[] imagem;

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
