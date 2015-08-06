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

import br.com.apostas.dto.TimeDTO;
import br.com.apostas.misc.ImagemConverter;
import br.com.apostas.model.Time;
import br.com.apostas.repositories.TimeRepository;

@TransactionManagement
@Stateless
public class TimeService extends GenericService {

	@Inject
	private TimeRepository timeRepository;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Time save(Time time) {
		try {
			time = getManager().merge(time);
			return time;
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null,
					e);
			return null;
		}
	}

	public List<TimeDTO> buscarTodosTimesDto() {
		List<TimeDTO> timesDto = new ArrayList<>();

		List<Time> times = timeRepository.getTodosTimes();

		if (!times.isEmpty()) {
			for (Time time : times) {

				TimeDTO timeDto = new TimeDTO();
				timeDto.oid = time.getOid();
				timeDto.nome = time.getNome();
				// timeDto.imagem =
				// ImagemConverter.converterImagemParaString(time.getImagem());
				timeDto.imagem = time.getImagem();

				timesDto.add(timeDto);
			}
		}

		return timesDto;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(String oidTime) {
		try {
			Time time = timeRepository.findByOid(oidTime);
			getManager().remove(time);
		} catch (Exception e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null,
					e);
		}
	}

}
