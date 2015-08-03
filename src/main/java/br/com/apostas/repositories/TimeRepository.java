package br.com.apostas.repositories;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.apostas.model.Time;
import br.com.apostas.model.Usuario;

@Named
@RequestScoped
public class TimeRepository extends GenericRepository<Time> {

	public TimeRepository() {
		super(Time.class);
	}
	
	public List<Time> getTodosTimes(){
		return getManager().createQuery("SELECT t FROM Time t ")
				.getResultList();
	}

}
