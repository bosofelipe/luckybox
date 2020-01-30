package com.luckybox.dto;

import java.util.List;

import com.luckybox.domain.LotteryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BetDTO {

	private LotteryType type;
	private List<Integer> dozens;
	private Integer foundByHits;
}
