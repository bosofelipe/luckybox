package com.luckybox.dto;

import lombok.Data;

@Data
public class HistoricDataSetDTO {

	private Long value;
	private Long count;
	private Long maxDiffBetweenConcurses;
	private Long lastConcurse;
	
}
