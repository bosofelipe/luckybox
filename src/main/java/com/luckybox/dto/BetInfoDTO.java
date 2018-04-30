package com.luckybox.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BetInfoDTO {

	private Integer hits;
	private List<Integer> dozenDrawn;
	private List<Integer> concurseDozens;
	private Long concurse;
	private Date concurseDate;
}
