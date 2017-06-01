package com.luckybox.dto;

import java.util.Date;

import com.luckybox.domain.Historic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricDTO {

	private Long concurse;
	private Date concurseDate;
	private Integer dozen1;
	private Integer dozen2;
	private Integer dozen3;
	private Integer dozen4;
	private Integer dozen5;
	private Integer dozen6;
	private Integer dozen7;
	private Integer dozen8;
	private Integer dozen9;
	private Integer dozen10;
	private Integer dozen11;
	private Integer dozen12;
	private Integer dozen13;
	private Integer dozen14;
	private Integer dozen15;

	public HistoricDTO(Historic historic) {
		this(historic.getConcurse(), historic.getConcurseDate(), historic.getDozen1(), historic.getDozen2(),
				historic.getDozen3(), historic.getDozen4(), historic.getDozen5(), historic.getDozen6(),
				historic.getDozen7(), historic.getDozen8(), historic.getDozen9(), historic.getDozen10(),
				historic.getDozen11(), historic.getDozen12(), historic.getDozen13(), historic.getDozen14(),
				historic.getDozen15());
	}

}
