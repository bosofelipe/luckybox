package com.luckybox.mapper;

import java.util.List;

import com.luckybox.domain.Bet;
import com.luckybox.dto.BetDTO;

import jersey.repackaged.com.google.common.collect.Lists;

public class BetMapper {

	public static Bet toEntity(BetDTO dto) {
		return Bet.builder().concurse(dto.getConcurse()).betId(dto.getId())
				.dozen1(dto.getDozen1()).dozen2(dto.getDozen2()).dozen3(dto.getDozen3()).dozen4(dto.getDozen4())
				.dozen5(dto.getDozen5()).dozen6(dto.getDozen6()).dozen7(dto.getDozen7()).dozen8(dto.getDozen8())
				.dozen9(dto.getDozen9()).dozen10(dto.getDozen10()).dozen11(dto.getDozen11()).dozen12(dto.getDozen12())
				.dozen13(dto.getDozen13()).dozen14(dto.getDozen14()).dozen15(dto.getDozen15()).build();
	}

	public static BetDTO toDTO(Bet bet) {
		return BetDTO.builder().concurse(bet.getConcurse()).date(bet.getCreationDate()).id(bet.getBetId())
				.dozen1(bet.getDozen1()).dozen2(bet.getDozen2()).dozen3(bet.getDozen3())
				.dozen4(bet.getDozen4()).dozen5(bet.getDozen5()).dozen6(bet.getDozen6())
				.dozen7(bet.getDozen7()).dozen8(bet.getDozen8()).dozen9(bet.getDozen9())
				.dozen10(bet.getDozen10()).dozen11(bet.getDozen11()).dozen12(bet.getDozen12())
				.dozen13(bet.getDozen13()).dozen14(bet.getDozen14()).dozen15(bet.getDozen15()).build();
	}

	
	public static List<Integer> toHistoricList(Bet bet) {
		return Lists.newArrayList(bet.getDozen1(), bet.getDozen2(), bet.getDozen3(), bet.getDozen4(), bet.getDozen5(),
				bet.getDozen6(), bet.getDozen7(), bet.getDozen8(), bet.getDozen9(), bet.getDozen10(), bet.getDozen11(),
				bet.getDozen12(), bet.getDozen13(), bet.getDozen14(), bet.getDozen15());
	}
}
