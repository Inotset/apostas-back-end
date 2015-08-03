package br.com.apostas.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Jogo extends GenericEntity {
	
	@Column(name = "oid_time1")
	private Time time1;
	
	@Column(name = "oid_time2")
	private Time time2;
	
	@Column(name = "placar_time1")
	private Integer placarTime1;
	
	@Column(name = "placar_time2")
	private Integer placarTime2;

	public Time getTime1() {
		return time1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public Time getTime2() {
		return time2;
	}

	public void setTime2(Time time2) {
		this.time2 = time2;
	}

	public Integer getPlacarTime1() {
		return placarTime1;
	}

	public void setPlacarTime1(Integer placarTime1) {
		this.placarTime1 = placarTime1;
	}

	public Integer getPlacarTime2() {
		return placarTime2;
	}

	public void setPlacarTime2(Integer placarTime2) {
		this.placarTime2 = placarTime2;
	}

}
