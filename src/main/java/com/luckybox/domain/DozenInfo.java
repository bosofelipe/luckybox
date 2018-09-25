package com.luckybox.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@lombok.Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dozeninfo")
public class DozenInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Integer number;

	private Long lastDrawNumber;

	private Long lastEfetiveConcurseNumber;

	private Long countDrawNumber;
	
	private Long maxSequenceDrawn;
	
	private Long qtSequenceDrawn;
	
	@OneToMany(mappedBy="dozenInfo")
	private List<DozenInfoSequence> dozenInfoSequence;
	
	private Long currentSequenceDrawn;
	@Enumerated(EnumType.STRING)
	private LotteryType type;

}
