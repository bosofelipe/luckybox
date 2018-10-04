package com.luckybox.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "combinationdozen")
public class CombinationDozen {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private LotteryType type;

	private Long concurse;
	
	private Integer keyValues;

	private String key;
}
