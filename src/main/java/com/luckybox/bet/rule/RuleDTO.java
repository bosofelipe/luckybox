package com.luckybox.bet.rule;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RuleDTO {

	private RuleType type;
	
	private Integer value;
}
