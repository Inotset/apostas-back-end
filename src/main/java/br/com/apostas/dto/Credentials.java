package br.com.apostas.dto;

import br.com.apostas.misc.Jsonable;

public class Credentials extends Jsonable {

    private String sessionId;
    private String email;
    private String nome;
    private String senha;
    private Boolean admin;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNome() {
    	return this.nome;
    }
    
    public void setNome(String nome) {
    	this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
