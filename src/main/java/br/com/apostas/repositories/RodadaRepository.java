package br.com.apostas.repositories;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.apostas.model.Rodada;

@Named
@RequestScoped
public class RodadaRepository extends GenericRepository<Rodada> {

	public RodadaRepository() {
		super(Rodada.class);
	}
	
	public Rodada findByNumeroRodada(Integer numeroRodada){
		return (Rodada) getManager().createQuery("select r from Rodada r where r.numero = :numeroRodada")
				.setParameter("numeroRodada", numeroRodada)
				.getSingleResult();
	}

}
