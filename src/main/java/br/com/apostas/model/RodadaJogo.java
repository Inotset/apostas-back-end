package br.com.apostas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rodada_jogo")
public class RodadaJogo extends GenericEntity {
	
	@Column(name = "oid_jogo")
	private Jogo jogo;
	
	@Column(name = "oid_rodada")
	private Rodada rodada;

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

}
