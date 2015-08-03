package br.com.apostas.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericService {
	
	@PersistenceContext(unitName = "apostas")
    private EntityManager manager;

    protected EntityManager getManager() {
        return manager;
    }
    
}
