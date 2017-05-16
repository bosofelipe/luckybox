package com.luckybox.mapper;

import java.util.List;

import com.luckybox.domain.Historic;
import com.luckybox.historic.dto.HistoricDTO;

import jersey.repackaged.com.google.common.collect.Lists;

public class HistoricMapper {

	public static Historic toEntity(HistoricDTO dto) {
		return Historic.builder().concurse(dto.getConcurse()).concurseDate(dto.getConcurseDate())
				.dozen1(dto.getDozen1()).dozen2(dto.getDozen2()).dozen3(dto.getDozen3()).dozen4(dto.getDozen4())
				.dozen5(dto.getDozen5()).dozen6(dto.getDozen6()).dozen7(dto.getDozen7()).dozen8(dto.getDozen8())
				.dozen9(dto.getDozen8()).dozen10(dto.getDozen10()).dozen11(dto.getDozen11()).dozen12(dto.getDozen12())
				.dozen13(dto.getDozen13()).dozen14(dto.getDozen14()).dozen15(dto.getDozen15()).build();
	}

	public static HistoricDTO toDTO(Historic historic) {
		return HistoricDTO.builder().concurse(historic.getConcurse()).concurseDate(historic.getConcurseDate())
				.dozen1(historic.getDozen1()).dozen2(historic.getDozen2()).dozen3(historic.getDozen3())
				.dozen4(historic.getDozen4()).dozen5(historic.getDozen5()).dozen6(historic.getDozen6())
				.dozen7(historic.getDozen7()).dozen8(historic.getDozen8()).dozen9(historic.getDozen8())
				.dozen10(historic.getDozen10()).dozen11(historic.getDozen11()).dozen12(historic.getDozen12())
				.dozen13(historic.getDozen13()).dozen14(historic.getDozen14()).dozen15(historic.getDozen15()).build();
	}

	public static List<Integer> toList(HistoricDTO dto) {
		return Lists.newArrayList(dto.getDozen1(), dto.getDozen2(), dto.getDozen3(), dto.getDozen4(), dto.getDozen5(),
				dto.getDozen6(), dto.getDozen7(), dto.getDozen8(), dto.getDozen9(), dto.getDozen10(), dto.getDozen11(),
				dto.getDozen12(), dto.getDozen13(), dto.getDozen14(), dto.getDozen15());
	}
	
	public static List<Integer> toList(Historic historic) {
		return Lists.newArrayList(historic.getDozen1(), historic.getDozen2(), historic.getDozen3(), historic.getDozen4(), historic.getDozen5(),
				historic.getDozen6(), historic.getDozen7(), historic.getDozen8(), historic.getDozen10(), historic.getDozen11(),
				historic.getDozen12(), historic.getDozen13(), historic.getDozen14(), historic.getDozen15());
	}
}
