package com.luckybox.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BetRuleSettings")
public class BetRuleSettings {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private LotteryType type;
	
	private Integer minSum;
	private Integer maxSum;
	
	private Integer minFibonacci;
	private Integer maxFibonacci;
	
	private Integer minFibonacciPrime;
	private Integer maxFibonacciPrime;
	
	private Integer minGreatherSequence;
	private Integer maxGreatherSequence;
	
	private Integer minQuantitySequence;
	private Integer maxQuantitySequence;
	

	private Integer minPrime;
	private Integer maxPrime;
	
	private Integer minPair;
	private Integer maxPair;
	
	private Integer minDozensLastRaffle;
	private Integer maxDozensLastRaffle;
	
	private Integer minSequence;
	private Integer maxSequence;
	
}
