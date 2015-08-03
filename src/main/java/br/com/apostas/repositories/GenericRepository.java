package br.com.apostas.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.apostas.model.GenericEntity;

public class GenericRepository<T extends GenericEntity> {
    
	@PersistenceContext(unitName = "apostas")
    private EntityManager manager;
    
    private final Class clazz;
    
    public GenericRepository(Class clazz) {
        this.clazz = clazz;
    }
    
    public T findByOid(String oid) {
        return (T) manager.find(clazz, oid);
    }
    
    /**
     * Esse construtor é necessário para satisfazer os requisitos do CDI.
     * @deprecated
     */
    @Deprecated
    public GenericRepository() {
        throw new InstantiationError("O Contrutor sem parâmetros do "
                + "GenericRepository não deve ser utilizado");
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }
    
    public StringBuffer getSQLWhere(StringBuffer sqlWhere) {
    	StringBuffer sbReturn;
    	if (sqlWhere.length() == 0) {
    		sbReturn = new StringBuffer(" where ");
    	} else {
			sbReturn = new StringBuffer(" and ");
		}
    	return sbReturn;
	}
}
