package com.luckybox.bet.rule;

import com.luckybox.domain.LotteryType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleDTO {

	private RuleType type;
	
	private Integer value;
	
	private LotteryType lotterType;
}
