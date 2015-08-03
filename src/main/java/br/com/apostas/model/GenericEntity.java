package br.com.apostas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import br.com.apostas.misc.Jsonable;

@MappedSuperclass
public class GenericEntity extends Jsonable {
    
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	protected String oid;
	
    @Version
    protected Long version;
    
    @Column(name = "dt_criacao", nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	protected Date criacao;
    
    @Column(name = "dt_alteracao", updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date alteracao;
    
    @PrePersist
    protected void onCreate() {
        criacao = new Date();
        alteracao = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
        alteracao = new Date();
    }   

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCriacao() {
        return criacao;
    }

    public Date getAlteracao() {
        return alteracao;
    }
}
