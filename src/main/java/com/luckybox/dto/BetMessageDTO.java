package com.luckybox.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonSerialize
public class BetMessageDTO {

	private Bet bet;
	private List<BetRule> rules;
}
