package br.com.apostas.security;

import java.util.Date;
import java.util.UUID;

import br.com.apostas.misc.Jsonable;
import br.com.apostas.model.Usuario;

public class Sessao extends Jsonable {
	
    private String oid;
    private String ip;
    private Date dataCriacao;
    private Usuario usuario;

    public Sessao(Usuario usuario, String ip) {
        this.ip = ip;
        this.usuario = usuario;
        this.dataCriacao = new Date();
        this.oid = UUID.randomUUID().toString();
    }

    public String getOid() {
        return oid;
    }

    public String getIp() {
        return ip;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
