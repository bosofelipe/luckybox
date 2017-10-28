package com.luckybox.mapper;

import java.util.List;

import com.luckybox.domain.Bet;
import com.luckybox.domain.CombinationDozens;
import com.luckybox.domain.Historic;
import com.luckybox.dto.DozenDTO;

import jersey.repackaged.com.google.common.collect.Lists;

public class DozenMapper {

	public static Bet toBet(DozenDTO dto) {
		return Bet.builder().concurse(dto.getConcurse()).betId(dto.getId())
				.dozen1(dto.getDozen1()).dozen2(dto.getDozen2()).dozen3(dto.getDozen3()).dozen4(dto.getDozen4())
				.dozen5(dto.getDozen5()).dozen6(dto.getDozen6()).dozen7(dto.getDozen7()).dozen8(dto.getDozen8())
				.dozen9(dto.getDozen9()).dozen10(dto.getDozen10()).dozen11(dto.getDozen11()).dozen12(dto.getDozen12())
				.dozen13(dto.getDozen13()).dozen14(dto.getDozen14()).dozen15(dto.getDozen15()).build();
	}
	
	public static CombinationDozens toCombinationDozens(DozenDTO dto) {
		return CombinationDozens.builder().id(dto.getId())
				.dozen1(dto.getDozen1()).dozen2(dto.getDozen2()).dozen3(dto.getDozen3()).dozen4(dto.getDozen4())
				.dozen5(dto.getDozen5()).dozen6(dto.getDozen6()).dozen7(dto.getDozen7()).dozen8(dto.getDozen8())
				.dozen9(dto.getDozen9()).dozen10(dto.getDozen10()).dozen11(dto.getDozen11()).dozen12(dto.getDozen12())
				.dozen13(dto.getDozen13()).dozen14(dto.getDozen14()).dozen15(dto.getDozen15()).build();
	}

	public static Historic toHistoric(DozenDTO dto) {
		return Historic.builder().concurse(dto.getConcurse()).concurseDate(dto.getConcurseDate())
				.dozen1(dto.getDozen1()).dozen2(dto.getDozen2()).dozen3(dto.getDozen3()).dozen4(dto.getDozen4())
				.dozen5(dto.getDozen5()).dozen6(dto.getDozen6()).dozen7(dto.getDozen7()).dozen8(dto.getDozen8())
				.dozen9(dto.getDozen9()).dozen10(dto.getDozen10()).dozen11(dto.getDozen11()).dozen12(dto.getDozen12())
				.dozen13(dto.getDozen13()).dozen14(dto.getDozen14()).dozen15(dto.getDozen15()).build();
	}
	
	public static DozenDTO toDTO(Bet bet) {
		return DozenDTO.builder().concurse(bet.getConcurse()).id(bet.getBetId()).date(bet.getCreationDate()).id(bet.getBetId())
				.dozen1(bet.getDozen1()).dozen2(bet.getDozen2()).dozen3(bet.getDozen3())
				.dozen4(bet.getDozen4()).dozen5(bet.getDozen5()).dozen6(bet.getDozen6())
				.dozen7(bet.getDozen7()).dozen8(bet.getDozen8()).dozen9(bet.getDozen9())
				.dozen10(bet.getDozen10()).dozen11(bet.getDozen11()).dozen12(bet.getDozen12())
				.dozen13(bet.getDozen13()).dozen14(bet.getDozen14()).dozen15(bet.getDozen15()).build();
	}
	
	public static DozenDTO toDTO(List<Integer> values) {
		return DozenDTO.builder().id(-1L).dozen1(values.get(0)).dozen2(values.get(1)).dozen3(values.get(2))
				.dozen4(values.get(3)).dozen5(values.get(4)).dozen6(values.get(5))
				.dozen7(values.get(6)).dozen8(values.get(7)).dozen9(values.get(8))
				.dozen10(values.get(9)).dozen11(values.get(10)).dozen12(values.get(11))
				.dozen13(values.get(12)).dozen14(values.get(13)).dozen15(values.get(14)).build();
	}
	
	public static DozenDTO toDTO(CombinationDozens combination) {
		return DozenDTO.builder().id(combination.getId())
				.dozen1(combination.getDozen1()).dozen2(combination.getDozen2()).dozen3(combination.getDozen3())
				.dozen4(combination.getDozen4()).dozen5(combination.getDozen5()).dozen6(combination.getDozen6())
				.dozen7(combination.getDozen7()).dozen8(combination.getDozen8()).dozen9(combination.getDozen9())
				.dozen10(combination.getDozen10()).dozen11(combination.getDozen11()).dozen12(combination.getDozen12())
				.dozen13(combination.getDozen13()).dozen14(combination.getDozen14()).dozen15(combination.getDozen15()).build();
	}
	
	public static DozenDTO toDTO(Historic historic) {
		return DozenDTO.builder().concurse(historic.getConcurse()).concurseDate(historic.getConcurseDate())
				.dozen1(historic.getDozen1()).dozen2(historic.getDozen2()).dozen3(historic.getDozen3())
				.dozen4(historic.getDozen4()).dozen5(historic.getDozen5()).dozen6(historic.getDozen6())
				.dozen7(historic.getDozen7()).dozen8(historic.getDozen8()).dozen9(historic.getDozen9())
				.dozen10(historic.getDozen10()).dozen11(historic.getDozen11()).dozen12(historic.getDozen12())
				.dozen13(historic.getDozen13()).dozen14(historic.getDozen14()).dozen15(historic.getDozen15()).build();
	}


	public static List<Integer> toList(DozenDTO combination) {
		return Lists.newArrayList(combination.getDozen1(), combination.getDozen2(), combination.getDozen3(), combination.getDozen4(), combination.getDozen5(),
				combination.getDozen6(), combination.getDozen7(), combination.getDozen8(), combination.getDozen9(), combination.getDozen10(), combination.getDozen11(),
				combination.getDozen12(), combination.getDozen13(), combination.getDozen14(), combination.getDozen15());
	}
	
	public static List<Integer> toList(Historic historic) {
		return Lists.newArrayList(historic.getDozen1(), historic.getDozen2(), historic.getDozen3(), historic.getDozen4(), historic.getDozen5(),
				historic.getDozen6(), historic.getDozen7(), historic.getDozen8(), historic.getDozen9(), historic.getDozen10(), historic.getDozen11(),
				historic.getDozen12(), historic.getDozen13(), historic.getDozen14(), historic.getDozen15());
	}
	
	public static List<Integer> toList(Bet bet) {
		return Lists.newArrayList(bet.getDozen1(), bet.getDozen2(), bet.getDozen3(), bet.getDozen4(), bet.getDozen5(),
				bet.getDozen6(), bet.getDozen7(), bet.getDozen8(), bet.getDozen9(), bet.getDozen10(), bet.getDozen11(),
				bet.getDozen12(), bet.getDozen13(), bet.getDozen14(), bet.getDozen15());
	}
}
