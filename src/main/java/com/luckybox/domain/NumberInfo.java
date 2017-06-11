package com.luckybox.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@lombok.Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "numberinfo")
public class NumberInfo {

	@Id
	private Integer number;

	private Long lastDrawNumber;

	private Long lastEfetiveConcurseNumber;

	private Long countDrawNumber;
	
	private Long maxSequenceDrawn;

}
