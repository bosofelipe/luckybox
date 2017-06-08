package com.luckybox.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "HistoricDataset")
public class HistoricDataset {

	@Id
	private Long concurse;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Historic historic;
	
	private Integer dozensLastRaffle;
	
	private Boolean alreadyDrawn;

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
