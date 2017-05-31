package com.luckybox.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinationDTO {

	private Long combinationId;
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

	public CombinationDTO(Combination combination) {
		this(combination.getCombinationId(), combination.getDozen1(), combination.getDozen2(),
				combination.getDozen3(), combination.getDozen4(), combination.getDozen5(), combination.getDozen6(),
				combination.getDozen7(), combination.getDozen8(), combination.getDozen9(), combination.getDozen10(),
				combination.getDozen11(), combination.getDozen12(), combination.getDozen13(), combination.getDozen14(),
				combination.getDozen15());
	}

}