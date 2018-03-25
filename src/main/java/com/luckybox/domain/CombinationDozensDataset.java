package com.luckybox.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "Combination_dozens_dataset")
public class CombinationDozensDataset {

	@Id
	private Long id;
	
	@OneToOne(mappedBy = "dataset")
	private CombinationDozens combinationDozens;
	
	private Integer dozenSum;

	private Integer average;

	private Integer pair;
	
	private Integer fibonacci;

	private Integer prime;
	
	private Integer fibonacciPrime;

	private Integer greatherSequence;
	
	private Integer qtdSequences;

	private Integer firstLine;

	private Integer secondLine;

	private Integer thirdLine;

	private Integer fourthLine;

	private Integer fivethLine;

	private Integer firstColumn;

	private Integer secondColumn;

	private Integer thirdColumn;

	private Integer fourthColumn;

	private Integer fivethColumn;
	
}

