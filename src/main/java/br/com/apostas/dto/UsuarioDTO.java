package br.com.apostas.dto;

import java.util.Date;

public class UsuarioDTO {
	
	public String oid;
	public Long version;
	public Date criacao;
	public Date alteracao;
	public String email;
	public String password;
	public String nome;
	public Boolean bloqueado;
	public Boolean admin;
	public Boolean inativo;
	public String imagemTime;

}
