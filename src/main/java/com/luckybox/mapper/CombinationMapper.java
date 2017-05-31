package com.luckybox.mapper;

import java.util.List;

import com.luckybox.domain.Combination;
import com.luckybox.domain.CombinationDTO;

import jersey.repackaged.com.google.common.collect.Lists;

public class CombinationMapper {

	public static Combination toEntity(CombinationDTO dto) {
		return Combination.builder().combinationId(dto.getCombinationId())
				.dozen1(dto.getDozen1()).dozen2(dto.getDozen2()).dozen3(dto.getDozen3()).dozen4(dto.getDozen4())
				.dozen5(dto.getDozen5()).dozen6(dto.getDozen6()).dozen7(dto.getDozen7()).dozen8(dto.getDozen8())
				.dozen9(dto.getDozen9()).dozen10(dto.getDozen10()).dozen11(dto.getDozen11()).dozen12(dto.getDozen12())
				.dozen13(dto.getDozen13()).dozen14(dto.getDozen14()).dozen15(dto.getDozen15()).build();
	}

	public static CombinationDTO toDTO(Combination combination) {
		return CombinationDTO.builder().combinationId(combination.getCombinationId())
				.dozen1(combination.getDozen1()).dozen2(combination.getDozen2()).dozen3(combination.getDozen3())
				.dozen4(combination.getDozen4()).dozen5(combination.getDozen5()).dozen6(combination.getDozen6())
				.dozen7(combination.getDozen7()).dozen8(combination.getDozen8()).dozen9(combination.getDozen9())
				.dozen10(combination.getDozen10()).dozen11(combination.getDozen11()).dozen12(combination.getDozen12())
				.dozen13(combination.getDozen13()).dozen14(combination.getDozen14()).dozen15(combination.getDozen15()).build();
	}

	public static List<Integer> toList(CombinationDTO combination) {
		return Lists.newArrayList(combination.getDozen1(), combination.getDozen2(), combination.getDozen3(), combination.getDozen4(), combination.getDozen5(),
				combination.getDozen6(), combination.getDozen7(), combination.getDozen8(), combination.getDozen9(), combination.getDozen10(), combination.getDozen11(),
				combination.getDozen12(), combination.getDozen13(), combination.getDozen14(), combination.getDozen15());
	}
}
