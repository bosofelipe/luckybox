package com.luckybox.dto;

import java.util.Date;

import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;

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
	
	private Integer dozen21;
	private Integer dozen22;
	private Integer dozen23;
	private Integer dozen24;
	private Integer dozen25;
	private Integer dozen26;
	private Integer dozen27;
	private Integer dozen28;
	private Integer dozen29;
	private Integer dozen30;
	
	private Integer dozen31;
	private Integer dozen32;
	private Integer dozen33;
	private Integer dozen34;
	private Integer dozen35;
	private Integer dozen36;
	private Integer dozen37;
	private Integer dozen38;
	private Integer dozen39;
	private Integer dozen40;
	
	private Integer dozen41;
	private Integer dozen42;
	private Integer dozen43;
	private Integer dozen44;
	private Integer dozen45;
	private Integer dozen46;
	private Integer dozen47;
	private Integer dozen48;
	private Integer dozen49;
	private Integer dozen50;
	private LotteryType type;
	
	private Integer concurses;
	
	private Date date;
	private Date concurseDate;
	private Boolean alreadyDrawn;
	
	public DozenDTO(Historic dozen) {
		this();
	}
}