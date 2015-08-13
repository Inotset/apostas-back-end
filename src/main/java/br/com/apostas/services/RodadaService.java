package br.com.apostas.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;

import br.com.apostas.dto.AdicionarPartidaDTO;
import br.com.apostas.dto.PartidaDTO;
import br.com.apostas.dto.TimeDTO;
import br.com.apostas.dto.TorneioDTO;
import br.com.apostas.model.Jogo;
import br.com.apostas.model.Rodada;
import br.com.apostas.model.RodadaJogo;
import br.com.apostas.model.Time;
import br.com.apostas.model.Torneio;
import br.com.apostas.repositories.JogoRepository;
import br.com.apostas.repositories.RodadaJogoRepository;
import br.com.apostas.repositories.RodadaRepository;

@TransactionManagement
@Stateless
public class RodadaService extends GenericService {

	@Inject
	private RodadaRepository rodadaRepository;
	
	@Inject
	private RodadaJogoRepository rodadaJogoRepository;
	
	@Inject
	private JogoRepository jogoRepository;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public AdicionarPartidaDTO save(AdicionarPartidaDTO partidaDto) {

		try {
			/*Rodada rodadaBanco = rodadaRepository.findByNumeroRodada(partidaDto.numeroRodada);

			RodadaJogo rodadaJogo = new RodadaJogo();

			if (rodadaBanco != null
					&& rodadaBanco.getNumero() != partidaDto.numeroRodada) {
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
			jogo.setDataJogo(partidaDto.dataPartida);
			getManager().merge(jogo);

			rodadaJogo.setJogo(jogo);
			getManager().merge(rodadaJogo);*/

			return partidaDto;
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null,
					e);
			return null;
		}
	}

	public static Torneio convertTorneio(TorneioDTO torneioDto) {
		Torneio torneio = new Torneio();
		torneio.setImagem(torneioDto.imagem);
		torneio.setNome(torneioDto.nome);
		torneio.setOid(torneioDto.oid);
		return torneio;
	}
	
	public static TorneioDTO convertTorneio(Torneio torneio) {
		TorneioDTO torneioDto = new TorneioDTO();
		torneioDto.imagem = torneio.getImagem();
		torneioDto.nome = torneio.getNome();
		torneioDto.oid = torneio.getOid();
		return torneioDto;
	}

	public static Time convertTime(TimeDTO timeDto) {
		Time time = new Time();
		time.setImagem(timeDto.imagem);
		time.setNome(timeDto.nome);
		time.setOid(timeDto.oid);
		return time;
	}
	
	public static TimeDTO convertTime(Time time) {
		TimeDTO timeDto = new TimeDTO();
		timeDto.imagem = time.getImagem();
		timeDto.nome = time.getNome();
		timeDto.oid = time.getOid();
		return timeDto;
	}
	
	public AdicionarPartidaDTO getDadosRodada(String oidRodada){
		AdicionarPartidaDTO adicionarPartidaDto = new AdicionarPartidaDTO();
		
		Rodada rodada = rodadaRepository.findByOid(oidRodada);
		List<RodadaJogo> jogosPorRodada = rodadaJogoRepository.findByOidRodada(oidRodada);
		
		adicionarPartidaDto.numeroRodada = rodada.getNumero();
		adicionarPartidaDto.torneio = convertTorneio(rodada.getTorneio());
		
		if (!jogosPorRodada.isEmpty()){
			for (RodadaJogo rodadaJogo : jogosPorRodada) {
				
				Jogo jogo = jogoRepository.findByOid(rodadaJogo.getJogo().getOid());
				
				PartidaDTO partidaDto = new PartidaDTO();
				partidaDto.dataPartida = jogo.getDataJogo();
				partidaDto.timeA = convertTime(jogo.getTime1());
				partidaDto.timeB = convertTime(jogo.getTime2());
				
				adicionarPartidaDto.partidas.add(partidaDto);
			}
		}
		
		return adicionarPartidaDto;
	}

}
