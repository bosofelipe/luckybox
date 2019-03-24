package com.luckybox.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "HistoricDataset")
public class HistoricDataset {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long concurse;
	
	@OneToOne(mappedBy = "dataset")
	private Historic historic;
	
	private Integer dozenSum;

	private Integer variationSum;

	private Integer dozensLastRaffle;

	private Integer pair;
	
	private Integer prime;
	
	private Integer fibonacci;
	
	private Integer qtdSequences;
	
	private Integer greatherSequence;

	@Enumerated(EnumType.STRING)
	private LotteryType type;
}
