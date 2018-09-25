package com.luckybox.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "Historic")
public class Historic {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long concurse;
	private Date concurseDate;
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
	@Enumerated(EnumType.STRING)
	private LotteryType type;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dataset_id")
	private HistoricDataset dataset;
	
}
