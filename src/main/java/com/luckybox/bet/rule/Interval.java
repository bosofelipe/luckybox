package com.luckybox.bet.rule;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Interval {

	private int min;
	private int max;
	private RuleType type;
	
}
