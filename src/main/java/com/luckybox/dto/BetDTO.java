package com.luckybox.dto;

import java.util.List;

import com.luckybox.domain.LotteryType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BetDTO {

	private LotteryType type;
	private List<Integer> dozens;
}
