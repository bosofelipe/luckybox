package com.luckybox.domain;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.luckybox.mapper.DozenMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Combination")
public class Combination {
	private static final List<Integer> FIBONACCI_SEQUENCE = asList(1, 2, 3, 5, 8, 13, 21, 34, 55, 89);
	private static final List<Integer> PRIME = asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
			61, 67, 71, 73, 79, 83, 89, 97);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer dozen1;
	private Integer dozen2;
	private Integer dozen3;
	private Integer dozen4;
	private Integer dozen5;
	private Integer dozen6;
	private Integer dozen7;
	private Integer dozen8;
	private Integer dozen9;
	private Integer dozen10;
	private Integer dozen11;
	private Integer dozen12;
	private Integer dozen13;
	private Integer dozen14;
	private Integer dozen15;
	private Integer dozen16;
	private Integer dozen17;
	private Integer dozen18;
	private Integer dozen19;
	private Integer dozen20;
	private Boolean alreadyDrawn;
	private Boolean ignored;
	private Date creationDate;

	private Integer dozenSum;
	private Integer pair;
	private Integer prime;
	private Integer fibonacci;
	private Integer qtdSequences;
	private Integer greatherSequence;

	@Enumerated(EnumType.STRING)
	private LotteryType type;

	private List<Integer> getList() {
		return DozenMapper.toList(this);
	}

	@Override
	public String toString() {
		return this.dozen1 + " " + this.dozen2 + this.dozen3 + " " + this.dozen4 + " " + this.dozen5 + " " + this.dozen6
				+ this.dozen7 + " " + this.dozen8 + " " + this.dozen9 + " " + this.dozen10 + " " + this.dozen11 + " "
				+ this.dozen12 + " " + this.dozen13 + " " + this.dozen14 + " " + this.dozen15;
	}

	public void setDozenSum() {
		this.dozenSum = getList().stream().mapToInt(Number::intValue).sum();
	}

	public void setPair() {
		this.pair = (int) getList().stream().filter(n -> n % 2 == 0).count();
	}

	public void setFibonacci() {
		this.fibonacci = (int) getList().stream().filter(containsValues(FIBONACCI_SEQUENCE)).mapToInt(c -> c).count();
	}

	public void setPrime() {
		this.prime = (int) getList().stream().filter(containsValues(PRIME)).mapToInt(c -> c).count();
	}
	
	public void setSequences() {
		List<Integer> values = getGreaterSequence();
		this.greatherSequence = values.get(0);
		this.qtdSequences = values.get(1);
	}

	private Predicate<? super Integer> containsValues(List<Integer> values) {
		return c -> values.contains(c);
	}

	private List<Integer> getGreaterSequence() {
		int count = 0;
		List<Integer> diffs = new ArrayList<>();
		for (int i = 0; i < getList().size() - 1; i++)
			count = countSequence(count, diffs, i);
		if (count != 0)
			diffs.add(count + 1);
		Collections.sort(diffs);
		Collections.reverse(diffs);
		List<Integer> values = new ArrayList<>();
		values.add(!diffs.isEmpty() ? diffs.get(0) : 0);
		values.add(diffs.size());
		return values;
	}

	private int countSequence(int count, List<Integer> diffs, int i) {
		int value = getList().get(i + 1) - getList().get(i);
		if (value == 1)
			count++;
		else {
			if (count != 0)
				diffs.add(count + 1);
			count = 0;
		}
		return count;
	}
}
