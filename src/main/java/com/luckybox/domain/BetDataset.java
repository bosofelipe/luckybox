package com.luckybox.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BetDataset")
public class BetDataset {

	@Id
	private Long id;
	
	@MapsId 
	@OneToOne(mappedBy = "dataset")
	@JoinColumn(name = "id")
	private Bet bet;
	
	private Integer sum;
	
	private Integer dozenSum;

	private Integer variationSum;

	private Integer average;

	private Integer pair;
	
	private Integer fibonacci;

	private Integer prime;

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
