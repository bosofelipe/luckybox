package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luckybox.domain.CombinationDataset;

public interface CombinationDatasetRepository extends JpaRepository<CombinationDataset, Long> {

	@Query(value = "update cds set already_drawn = true from CombinationDataset cds inner join Combination c2 on cds.combinationId = c2.combinationId"
			+ "where c2.dozen1= ?1 and c2.dozen2= ?2 and c2.dozen3= ?3 and c2.dozen4= ?4 and c2.dozen5= ?5 and c2.dozen6= ?6"
			+ "and c2.dozen7= ?7 and c2.dozen8= ?8 and c2.dozen9= ?9 and c2.dozen10= ?10 and c2.dozen11= ?11 and c2.dozen12= ?12 and c2.dozen13= ?13 and c2.dozen14= ?14 and c2.dozen15= ?15")
	public void markDrawnByDozen(Integer dozen1, Integer dozen2, Integer dozen3, Integer dozen4, Integer dozen5,
			Integer dozen6, Integer dozen7, Integer dozen8, Integer dozen9, Integer dozen10, Integer dozen11,
			Integer dozen12, Integer dozen13, Integer dozen14, Integer dozen15);

}
