package com.luckybox.bet.rule;

import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RuleDTO {

	private RuleType type;
	
	private Integer value;
	
	private LotteryType lotterType;
	
	private DozenDTO dozens;
}
