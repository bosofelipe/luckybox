package com.luckybox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.filter.ObjectFilter;

public interface HistoricDatasetRepository extends JpaRepository<HistoricDataset, Long>{
	
	HistoricDataset findByConcurse(Long concurse);

	//select count(sum) as co, sum from historic_dataset group by sum order by co desc limit 50
	@Query(value = "select new com.luckybox.filter.ObjectFilter(h.sum, count(h.sum)) from HistoricDataset h group by h.sum order by count(h.sum)")
	public List<ObjectFilter> filterSum();

}
