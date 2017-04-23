package com.luckybox.service.importer;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.historic.dto.HistoricDTO;
import com.luckybox.mapper.HistoricMapper;

public class HistoricDatasetCreator {

	public HistoricDataset create(HistoricDTO historicDTO){
		Integer sumDozens = sumDozens(historicDTO);
		return HistoricDataset.builder()
				.sum(sumDozens)
				.average(sumDozens / 15)
				.pair(countPair(historicDTO))
				.concurse(historicDTO.getConcurse())
				.build();
	}
	
	private Integer sumDozens(HistoricDTO historicDTO) {
		return HistoricMapper.toList(historicDTO).stream().mapToInt(i -> i).sum();
	}
	
	private Integer countPair(HistoricDTO historicDTO){
		return (int) HistoricMapper.toList(historicDTO).stream().filter(n -> n % 2 == 0).count();
	}
	
}
