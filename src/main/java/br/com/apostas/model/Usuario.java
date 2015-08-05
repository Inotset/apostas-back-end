package br.com.apostas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario extends GenericEntity {

	private String email;

	private String password;

	private String nome;

	private Boolean bloqueado;

	private Boolean admin;

	@Column(name = "id_inativo")
	private Boolean inativo;

	private byte[] imagem;

	@ManyToOne
	@JoinColumn(name = "oid_time")
	private Time time;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getInativo() {
		if (inativo == null) {
			return false;
		}
		return inativo;
	}

	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
}
