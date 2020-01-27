package com.luckybox.mapper;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.luckybox.domain.Bet;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.BetDTO;
import com.luckybox.dto.DozenDTO;

public class DozenMapper {

	private DozenMapper() {
	}

	public static Bet toBet(DozenDTO dto) {
		return Bet.builder().concurse(dto.getConcurse()).type(dto.getType()).betId(dto.getId()).dozen1(dto.getDozen1())
				.dozen2(dto.getDozen2()).dozen3(dto.getDozen3()).dozen4(dto.getDozen4()).dozen5(dto.getDozen5())
				.dozen6(dto.getDozen6()).dozen7(dto.getDozen7()).dozen8(dto.getDozen8()).dozen9(dto.getDozen9())
				.dozen10(dto.getDozen10()).dozen11(dto.getDozen11()).dozen12(dto.getDozen12()).dozen13(dto.getDozen13())
				.dozen14(dto.getDozen14()).dozen15(dto.getDozen15()).dozen16(dto.getDozen16()).dozen17(dto.getDozen17())
				.dozen18(dto.getDozen18()).dozen19(dto.getDozen19()).dozen20(dto.getDozen20()).dozen21(dto.getDozen21())
				.dozen22(dto.getDozen22()).dozen23(dto.getDozen23()).dozen24(dto.getDozen24()).dozen25(dto.getDozen25())
				.dozen26(dto.getDozen26()).dozen27(dto.getDozen27()).dozen28(dto.getDozen28()).dozen29(dto.getDozen29())
				.dozen30(dto.getDozen30()).dozen31(dto.getDozen31()).dozen32(dto.getDozen32()).dozen33(dto.getDozen33())
				.dozen34(dto.getDozen34()).dozen35(dto.getDozen35()).dozen36(dto.getDozen36()).dozen37(dto.getDozen37())
				.dozen38(dto.getDozen38()).dozen39(dto.getDozen39()).dozen40(dto.getDozen40()).dozen41(dto.getDozen41())
				.dozen42(dto.getDozen42()).dozen43(dto.getDozen43()).dozen44(dto.getDozen44()).dozen45(dto.getDozen45())
				.dozen46(dto.getDozen46()).dozen47(dto.getDozen47()).dozen48(dto.getDozen48()).dozen49(dto.getDozen49())
				.dozen50(dto.getDozen50()).build();
	}

	public static Historic toHistoric(DozenDTO dto) {
		return Historic.builder().type(dto.getType()).concurse(dto.getConcurse()).concurseDate(dto.getConcurseDate())
				.dozen1(dto.getDozen1()).dozen2(dto.getDozen2()).dozen3(dto.getDozen3()).dozen4(dto.getDozen4())
				.dozen5(dto.getDozen5()).dozen6(dto.getDozen6()).dozen7(dto.getDozen7()).dozen8(dto.getDozen8())
				.dozen9(dto.getDozen9()).dozen10(dto.getDozen10()).dozen11(dto.getDozen11()).dozen12(dto.getDozen12())
				.dozen13(dto.getDozen13()).dozen14(dto.getDozen14()).dozen15(dto.getDozen15()).dozen16(dto.getDozen16())
				.dozen17(dto.getDozen17()).dozen18(dto.getDozen18()).dozen19(dto.getDozen19()).dozen20(dto.getDozen20())
				.build();
	}

	public static DozenDTO toDTO(Bet bet) {
		return DozenDTO.builder().type(bet.getType()).concurse(bet.getConcurse()).id(bet.getBetId())
				.date(bet.getCreationDate()).id(bet.getBetId()).dozen1(bet.getDozen1()).dozen2(bet.getDozen2())
				.dozen3(bet.getDozen3()).dozen4(bet.getDozen4()).dozen5(bet.getDozen5()).dozen6(bet.getDozen6())
				.dozen7(bet.getDozen7()).dozen8(bet.getDozen8()).dozen9(bet.getDozen9()).dozen10(bet.getDozen10())
				.dozen11(bet.getDozen11()).dozen12(bet.getDozen12()).dozen13(bet.getDozen13()).dozen14(bet.getDozen14())
				.dozen15(bet.getDozen15()).dozen16(bet.getDozen16()).dozen17(bet.getDozen17()).dozen18(bet.getDozen18())
				.dozen19(bet.getDozen19()).dozen20(bet.getDozen20()).dozen21(bet.getDozen21()).dozen22(bet.getDozen22())
				.dozen23(bet.getDozen23()).dozen24(bet.getDozen24()).dozen25(bet.getDozen25()).dozen26(bet.getDozen26())
				.dozen27(bet.getDozen27()).dozen28(bet.getDozen28()).dozen29(bet.getDozen29()).dozen30(bet.getDozen30())
				.dozen31(bet.getDozen31()).dozen32(bet.getDozen32()).dozen33(bet.getDozen33()).dozen34(bet.getDozen34())
				.dozen35(bet.getDozen35()).dozen36(bet.getDozen36()).dozen37(bet.getDozen37()).dozen38(bet.getDozen38())
				.dozen39(bet.getDozen39()).dozen40(bet.getDozen40()).dozen41(bet.getDozen41()).dozen42(bet.getDozen42())
				.dozen43(bet.getDozen43()).dozen44(bet.getDozen44()).dozen45(bet.getDozen45()).dozen46(bet.getDozen46())
				.dozen47(bet.getDozen47()).dozen48(bet.getDozen48()).dozen49(bet.getDozen49()).dozen50(bet.getDozen50())
				.build();
	}

	public static DozenDTO toDTO(List<Integer> values) {
		return DozenDTO.builder().id(-1L).dozen1(values.get(0)).dozen2(values.get(1)).dozen3(values.get(2))
				.dozen4(values.get(3)).dozen5(values.get(4)).dozen6(values.get(5)).dozen7(values.get(6))
				.dozen8(values.get(7)).dozen9(values.get(8)).dozen10(values.get(9)).dozen11(values.get(10))
				.dozen12(values.get(11)).dozen13(values.get(12)).dozen14(values.get(13)).dozen15(values.get(14))
				.dozen16(values.get(15)).dozen17(values.get(16)).dozen18(values.get(17)).dozen19(values.get(18))
				.dozen20(values.get(19)).dozen21(values.get(20)).dozen22(values.get(21)).dozen23(values.get(22))
				.dozen24(values.get(23)).dozen25(values.get(24)).dozen26(values.get(25)).dozen27(values.get(26))
				.dozen28(values.get(27)).dozen29(values.get(28)).dozen30(values.get(29)).dozen31(values.get(30))
				.dozen32(values.get(31)).dozen33(values.get(32)).dozen34(values.get(33)).dozen35(values.get(34))
				.dozen36(values.get(35)).dozen37(values.get(36)).dozen38(values.get(37)).dozen39(values.get(38))
				.dozen40(values.get(39)).dozen41(values.get(40)).dozen42(values.get(41)).dozen43(values.get(42))
				.dozen44(values.get(43)).dozen45(values.get(44)).dozen46(values.get(45)).dozen47(values.get(46))
				.dozen48(values.get(47)).dozen49(values.get(48)).dozen50(values.get(49)).build();
	}

	public static DozenDTO toDTO(List<Integer> values, LotteryType type) {
		if (type == LotteryType.LOTOMANIA) {
			return DozenDTO.builder().id(-1L).dozen1(values.get(0)).dozen2(values.get(1)).dozen3(values.get(2))
					.dozen4(values.get(3)).dozen5(values.get(4)).dozen6(values.get(5)).dozen7(values.get(6))
					.dozen8(values.get(7)).dozen9(values.get(8)).dozen10(values.get(9)).dozen11(values.get(10))
					.dozen12(values.get(11)).dozen13(values.get(12)).dozen14(values.get(13)).dozen15(values.get(14))
					.dozen16(values.get(15)).dozen17(values.get(16)).dozen18(values.get(17)).dozen19(values.get(18))
					.dozen20(values.get(19)).dozen21(values.get(20)).dozen22(values.get(21)).dozen23(values.get(22))
					.dozen24(values.get(23)).dozen25(values.get(24)).dozen26(values.get(25)).dozen27(values.get(26))
					.dozen28(values.get(27)).dozen29(values.get(28)).dozen30(values.get(29)).dozen31(values.get(30))
					.dozen32(values.get(31)).dozen33(values.get(32)).dozen34(values.get(33)).dozen35(values.get(34))
					.dozen36(values.get(35)).dozen37(values.get(36)).dozen38(values.get(37)).dozen39(values.get(38))
					.dozen40(values.get(39)).dozen41(values.get(40)).dozen42(values.get(41)).dozen43(values.get(42))
					.dozen44(values.get(43)).dozen45(values.get(44)).dozen46(values.get(45)).dozen47(values.get(46))
					.dozen48(values.get(47)).dozen49(values.get(48)).dozen50(values.get(49)).type(type).build();
		} else if (type == LotteryType.LOTOFACIL) {
			return DozenDTO.builder().id(-1L).dozen1(values.get(0)).dozen2(values.get(1)).dozen3(values.get(2))
					.dozen4(values.get(3)).dozen5(values.get(4)).dozen6(values.get(5)).dozen7(values.get(6))
					.dozen8(values.get(7)).dozen9(values.get(8)).dozen10(values.get(9)).dozen11(values.get(10))
					.dozen12(values.get(11)).dozen13(values.get(12)).dozen14(values.get(13)).dozen15(values.get(14))
					.type(type).build();
		} else if (type == LotteryType.MEGASENA) {
			return DozenDTO.builder().id(-1L).dozen1(values.get(0)).dozen2(values.get(1)).dozen3(values.get(2))
					.dozen4(values.get(3)).dozen5(values.get(4)).dozen6(values.get(5)).type(type).build();
		} else {
			return DozenDTO.builder().id(-1L).dozen1(values.get(0)).dozen2(values.get(1)).dozen3(values.get(2))
					.dozen4(values.get(3)).dozen5(values.get(4)).type(type).build();
		}
	}

	public static DozenDTO toDTO(Historic historic) {
		return DozenDTO.builder().concurse(historic.getConcurse()).concurseDate(historic.getConcurseDate())
				.dozen1(historic.getDozen1()).dozen2(historic.getDozen2()).dozen3(historic.getDozen3())
				.dozen4(historic.getDozen4()).dozen5(historic.getDozen5()).dozen6(historic.getDozen6())
				.dozen7(historic.getDozen7()).dozen8(historic.getDozen8()).dozen9(historic.getDozen9())
				.dozen10(historic.getDozen10()).dozen11(historic.getDozen11()).dozen12(historic.getDozen12())
				.dozen13(historic.getDozen13()).dozen14(historic.getDozen14()).dozen15(historic.getDozen15())
				.dozen16(historic.getDozen16()).dozen17(historic.getDozen17()).dozen18(historic.getDozen18())
				.dozen19(historic.getDozen19()).dozen20(historic.getDozen20()).type(historic.getType()).build();
	}

	public static List<Integer> toList(DozenDTO combination) {
		List<Integer> dozens = Lists.newArrayList(combination.getDozen1(), combination.getDozen2(),
				combination.getDozen3(), combination.getDozen4(), combination.getDozen5(), combination.getDozen6(),
				combination.getDozen7(), combination.getDozen8(), combination.getDozen9(), combination.getDozen10(),
				combination.getDozen11(), combination.getDozen12(), combination.getDozen13(), combination.getDozen14(),
				combination.getDozen15(), combination.getDozen16(), combination.getDozen17(), combination.getDozen18(),
				combination.getDozen19(), combination.getDozen20(), combination.getDozen21(), combination.getDozen22(),
				combination.getDozen23(), combination.getDozen24(), combination.getDozen25(), combination.getDozen26(),
				combination.getDozen27(), combination.getDozen28(), combination.getDozen29(), combination.getDozen30(),
				combination.getDozen31(), combination.getDozen32(), combination.getDozen33(), combination.getDozen34(),
				combination.getDozen35(), combination.getDozen36(), combination.getDozen37(), combination.getDozen38(),
				combination.getDozen39(), combination.getDozen40(), combination.getDozen41(), combination.getDozen42(),
				combination.getDozen43(), combination.getDozen44(), combination.getDozen45(), combination.getDozen46(),
				combination.getDozen47(), combination.getDozen48(), combination.getDozen49(), combination.getDozen50());
		dozens.removeIf(Objects::isNull);
		return dozens;
	}

	public static List<Integer> toList(Historic historic) {
		List<Integer> dozens = Lists.newArrayList(historic.getDozen1(), historic.getDozen2(), historic.getDozen3(),
				historic.getDozen4(), historic.getDozen5(), historic.getDozen6(), historic.getDozen7(),
				historic.getDozen8(), historic.getDozen9(), historic.getDozen10(), historic.getDozen11(),
				historic.getDozen12(), historic.getDozen13(), historic.getDozen14(), historic.getDozen15(),
				historic.getDozen16(), historic.getDozen17(), historic.getDozen18(), historic.getDozen19(),
				historic.getDozen20());
		dozens.removeIf(Objects::isNull);
		return dozens;
	}

	public static List<Integer> toList(Bet bet) {
		List<Integer> dozens = Lists.newArrayList(bet.getDozen1(), bet.getDozen2(), bet.getDozen3(), bet.getDozen4(),
				bet.getDozen5(), bet.getDozen6(), bet.getDozen7(), bet.getDozen8(), bet.getDozen9(), bet.getDozen10(),
				bet.getDozen11(), bet.getDozen12(), bet.getDozen13(), bet.getDozen14(), bet.getDozen15(),
				bet.getDozen16(), bet.getDozen17(), bet.getDozen18(), bet.getDozen19(), bet.getDozen20(),
				bet.getDozen21(), bet.getDozen22(), bet.getDozen23(), bet.getDozen24(), bet.getDozen25(),
				bet.getDozen26(), bet.getDozen27(), bet.getDozen28(), bet.getDozen29(), bet.getDozen30(),
				bet.getDozen31(), bet.getDozen32(), bet.getDozen33(), bet.getDozen34(), bet.getDozen35(),
				bet.getDozen36(), bet.getDozen37(), bet.getDozen38(), bet.getDozen39(), bet.getDozen40(),
				bet.getDozen41(), bet.getDozen42(), bet.getDozen43(), bet.getDozen44(), bet.getDozen45(),
				bet.getDozen46(), bet.getDozen47(), bet.getDozen48(), bet.getDozen49(), bet.getDozen50());
		dozens.removeIf(Objects::isNull);
		return dozens;
	}

	public static List<Integer> toList(BetDTO betDTO) {
		List<Integer> dozens = Lists.newArrayList();
		for (int i = 0; i < betDTO.getDozens().size(); i++) {
			dozens.add(betDTO.getDozens().get(i));
		}
		return dozens;
	}
}
