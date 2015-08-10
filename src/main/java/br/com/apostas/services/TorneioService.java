package br.com.apostas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;

import br.com.apostas.dto.TorneioDTO;
import br.com.apostas.model.Torneio;
import br.com.apostas.repositories.TorneioRepository;

@TransactionManagement
@Stateless
public class TorneioService extends GenericService {
	
	@Inject
	private TorneioRepository torneioRepository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Torneio save(Torneio torneio) {
		try {
			torneio = getManager().merge(torneio);
			return torneio;
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
	
	public List<TorneioDTO> buscarTodosTorneiosDto(){
		List<TorneioDTO> torneiosDto = new ArrayList<>();
		
		List<Torneio> torneios = torneioRepository.getTodosTorneios();
		
		if (!torneios.isEmpty()){
			for (Torneio torneio : torneios) {
				
				TorneioDTO torneioDto = new TorneioDTO();
				torneioDto.oid = torneio.getOid();
				torneioDto.nome = torneio.getNome();
				// torneioDto.imagem = ImagemConverter.converterImagemParaString(torneio.getImagem());
				torneioDto.imagem = torneio.getImagem();
				
				torneiosDto.add(torneioDto);
			}
		}
		return torneiosDto;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(String oidTorneio) {
		try {
			Torneio torneio = torneioRepository.findByOid(oidTorneio);
			getManager().remove(torneio);
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null,
					e);
		}
	}

}
