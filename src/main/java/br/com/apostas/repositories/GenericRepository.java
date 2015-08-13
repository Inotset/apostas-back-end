package br.com.apostas.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public Integer getProximoNumero() {
    	Integer numero;
    	try {    		
    		Query q = getManager().createQuery("select c.numero from " + clazz.getSimpleName() +" c where c.numero IS NOT NULL order by c.numero DESC").setMaxResults(1);
    		numero = (Integer) q.getSingleResult();
    		numero += 1; 
		} catch (Exception e) {
			numero = 1;
		}
    	return numero;
    }
}
