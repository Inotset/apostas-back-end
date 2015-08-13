package br.com.apostas.repositories;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.apostas.model.Jogo;

@Named
@RequestScoped
public class JogoRepository extends GenericRepository<Jogo> {

	public JogoRepository() {
		super(Jogo.class);
	}

}
