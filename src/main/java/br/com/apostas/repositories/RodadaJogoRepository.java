package br.com.apostas.repositories;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.apostas.model.RodadaJogo;

@Named
@RequestScoped
public class RodadaJogoRepository extends GenericRepository<RodadaJogo> {

	public RodadaJogoRepository() {
		super(RodadaJogo.class);
	}
	
	public List<RodadaJogo> findByOidRodada(String oidRodada){
		return  getManager().createQuery("select r from RodadaJogo r where r.rodada.oid = :oidRodada")
				.setParameter("oidRodada", oidRodada)
				.getResultList();
	}

}