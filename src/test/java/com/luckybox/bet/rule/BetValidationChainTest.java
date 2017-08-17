package com.luckybox.bet.rule;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.luckybox.dto.DozenDTO;

public class BetValidationChainTest {

	BetValidationChain chainValidation = new BetValidationChain();
	
	@Test
	public void catchRulePrime() throws Exception {
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTO());
		assertThat(validationChain.size(), CoreMatchers.equalTo(1));
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.PRIME));
	}
	
	@Test
	public void catchRulePair() throws Exception {
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTOWithUnpairs());
		assertThat(validationChain.size(), CoreMatchers.equalTo(1));
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.PAIR));
	}

	@Test
	public void catchRuleFirstColumn() throws Exception {
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTOWithFirstLineDozens());
		assertThat(validationChain.size(), CoreMatchers.equalTo(1));
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.FIRST_LINE));
	}

	
	private DozenDTO createDozenDTO() {
		return DozenDTO.builder()
		.dozen1(2)
		.dozen2(3)
		.dozen3(4)
		.dozen4(5)
		.dozen5(7)
		.dozen6(8)
		.dozen7(10)
		.dozen8(11)
		.dozen9(12)
		.dozen10(13)
		.dozen11(15)
		.dozen12(17)
		.dozen13(21)
		.dozen14(24)
		.dozen15(25).build();
	}
	
	private DozenDTO createDozenDTOWithUnpairs() {
		return DozenDTO.builder()
		.dozen1(1)
		.dozen2(3)
		.dozen3(4)
		.dozen4(7)
		.dozen5(8)
		.dozen6(10)
		.dozen7(11)
		.dozen8(12)
		.dozen9(14)
		.dozen10(15)
		.dozen11(17)
		.dozen12(19)
		.dozen13(21)
		.dozen14(23)
		.dozen15(25).build();
	}
	
	private DozenDTO createDozenDTOWithFirstLineDozens() {
		return DozenDTO.builder()
		.dozen1(1)
		.dozen2(2)
		.dozen3(3)
		.dozen4(4)
		.dozen5(5)
		.dozen6(8)
		.dozen7(10)
		.dozen8(11)
		.dozen9(12)
		.dozen10(13)
		.dozen11(15)
		.dozen12(17)
		.dozen13(21)
		.dozen14(24)
		.dozen15(25).build();
	}
	
	
}
