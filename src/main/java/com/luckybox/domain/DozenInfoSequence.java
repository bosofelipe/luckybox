package com.luckybox.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@lombok.Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dozeninfosequence")
public class DozenInfoSequence {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Integer quantity;

	private Long initialConcurse;

	private Long finalConcurse;
	
	@ManyToOne
    @JoinColumn(name="dozen_info_id", nullable=true)
    private DozenInfo dozenInfo;

}
