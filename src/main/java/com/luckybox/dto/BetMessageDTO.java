package com.luckybox.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.luckybox.bet.rule.RuleType;
import com.luckybox.domain.Bet;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonSerialize
public class BetMessageDTO {

	private Bet bet;
	private List<RuleType> rules;
}
