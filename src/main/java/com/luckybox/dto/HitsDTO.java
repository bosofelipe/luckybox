package com.luckybox.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonSerialize
public class HitsDTO {

	Long concurse;
	Integer hits;
}
