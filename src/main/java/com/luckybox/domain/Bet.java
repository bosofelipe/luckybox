package com.luckybox.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
@Table(name = "Bet")
public class Bet {

	@Id
	private Long betId;
	private Long concurse;
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
	private Date creationDate;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bet", cascade = CascadeType.ALL)
	private BetDataset dataset;
	
	@PrePersist
	public void prePersist(){
		this.creationDate = new Date();
	}
	
	
	
}