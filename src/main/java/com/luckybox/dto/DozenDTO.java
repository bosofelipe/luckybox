package com.luckybox.dto;

import java.util.Date;

import com.luckybox.domain.Bet;
import com.luckybox.domain.CombinationDozens;
import com.luckybox.domain.Historic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DozenDTO {

	private Long id;
	private String combinationId;
	private Long concurse;
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
	private Integer dozen16;
	private Integer dozen17;
	private Integer dozen18;
	private Integer dozen19;
	private Integer dozen20;
	
	private Date date;
	private Date concurseDate;
	private Boolean alreadyDrawn;
	
	public DozenDTO(Historic dozen) {
		this();
	}
	
	public DozenDTO(Bet bet) {
		this();
	}
	
	public DozenDTO(CombinationDozens combination) {
		this();
	}

}