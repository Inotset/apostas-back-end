package br.com.apostas.repositories;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.apostas.model.Torneio;

@Named
@RequestScoped
public class TorneioRepository extends GenericRepository<Torneio> {

	public TorneioRepository() {
		super(Torneio.class);
	}
	
	public List<Torneio> getTodosTorneios(){
		return getManager().createQuery("SELECT t FROM Torneio t ")
				.getResultList();
	}

}
