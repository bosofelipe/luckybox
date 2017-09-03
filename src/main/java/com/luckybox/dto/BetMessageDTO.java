package com.luckybox.dto;

import java.util.List;

import com.luckybox.bet.rule.RuleType;
import com.luckybox.domain.Bet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BetMessageDTO {

	private List<RuleType> rules;
	private Bet bet;
}
