package com.luckybox.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupBetMessageDTO {

	@JsonProperty("message")
	private List<BetMessageDTO> message;
}
