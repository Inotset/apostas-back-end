package br.com.apostas.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;

import br.com.apostas.dto.AdicionarPartidaDTO;
import br.com.apostas.dto.TimeDTO;
import br.com.apostas.dto.TorneioDTO;
import br.com.apostas.model.Jogo;
import br.com.apostas.model.Rodada;
import br.com.apostas.model.RodadaJogo;
import br.com.apostas.model.Time;
import br.com.apostas.model.Torneio;
import br.com.apostas.repositories.RodadaRepository;

@TransactionManagement
@Stateless
public class RodadaService extends GenericService {
	
	@Inject
	private RodadaRepository rodadaRepository;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public AdicionarPartidaDTO save(AdicionarPartidaDTO partidaDto) {
		
		try {
			Rodada rodadaBanco = rodadaRepository.findByNumeroRodada(partidaDto.numeroRodada);
			
			RodadaJogo rodadaJogo = new RodadaJogo();
			
			if (rodadaBanco != null && rodadaBanco.getNumero() != partidaDto.numeroRodada){
				Rodada rodada = new Rodada();
				rodada.setNumero(partidaDto.numeroRodada);
				rodada.setTorneio(convertTorneio(partidaDto.torneio));
				getManager().merge(rodada);
				
				rodadaJogo.setRodada(rodada);
			} else {
				rodadaJogo.setRodada(rodadaBanco);
			}
			
			Jogo jogo = new Jogo();
			jogo.setTime1(convertTime(partidaDto.timeA));
			jogo.setTime2(convertTime(partidaDto.timeB));
			getManager().merge(jogo);
			
			rodadaJogo.setJogo(jogo);
			getManager().merge(rodadaJogo);
			
			return partidaDto;
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
	
	public static Torneio convertTorneio(TorneioDTO torneioDto){
		Torneio torneio = new Torneio();
		torneio.setImagem(torneioDto.imagem);
		torneio.setNome(torneioDto.nome);
		torneio.setOid(torneioDto.oid);
		return torneio;
	}
	
	public static Time convertTime(TimeDTO timeDto){
		Time time = new Time();
		time.setImagem(timeDto.imagem);
		time.setNome(timeDto.nome);
		time.setOid(timeDto.oid);
		return time;
	}

}
