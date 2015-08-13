package br.com.apostas.dto;

import java.util.ArrayList;
import java.util.List;

public class AdicionarPartidaDTO {
	
	public Integer numeroRodada;
	public TorneioDTO torneio;
	public List<PartidaDTO> partidas = new ArrayList<>();

}
