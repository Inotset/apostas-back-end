package br.com.apostas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "torneio_time")
public class TorneioTime extends GenericEntity {
	
	@Column(name = "oid_time")
	private Time time;
	
	@Column(name = "oid_torneio")
	private Torneio torneio;
	
	private Boolean bloqueado;

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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
