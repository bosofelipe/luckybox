package com.luckybox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.filter.ObjectFilter;

public interface HistoricDatasetRepository extends JpaRepository<HistoricDataset, Long>{
	
	public HistoricDataset findByConcurse(Long concurse);

	@Query(value = "select new com.luckybox.filter.ObjectFilter(h.dozenSum, count(h.dozenSum)) from HistoricDataset h group by h.dozenSum order by count(h.dozenSum)")
	public List<ObjectFilter> filterSum();

}
