package com.luckybox.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Combination_dozens")
@EqualsAndHashCode
public class CombinationDozens {
	
	@Id
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
	private Boolean alreadyDrawn;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dataset_id")
	private CombinationDozensDataset dataset;
}