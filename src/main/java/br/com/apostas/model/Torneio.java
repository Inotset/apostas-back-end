package br.com.apostas.model;

import javax.persistence.Entity;

@Entity
public class Torneio extends GenericEntity {
	
	private String nome;
	
	private String imagem;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
