package com.luckybox.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luckybox.domain.Historic;

public interface HistoricRepository extends JpaRepository<Historic, Long> {

	public static final String DOZENS = "FROM Historic r WHERE r.dozen1 = ?1 and r.dozen2 = ?2 and r.dozen3 = ?3 and r.dozen4 = ?4 and r.dozen5 = ?5 and r.dozen6 = ?6 and r.dozen7 = ?7 and r.dozen8 = ?8 and r.dozen9 = ?9 and r.dozen10 = ?10 and r.dozen11 = ?11 and r.dozen12 = ?12 and r.dozen13 = ?13 and r.dozen14 = ?14 and r.dozen15 = ?15";

	public static final String DOZENS_NE_CONCURSE = "FROM Historic r WHERE r.dozen1 = ?1 and r.dozen2 = ?2 and r.dozen3 = ?3 and r.dozen4 = ?4 and r.dozen5 = ?5 and r.dozen6 = ?6 and r.dozen7 = ?7 and r.dozen8 = ?8 and r.dozen9 = ?9 and r.dozen10 = ?10 and r.dozen11 = ?11 and r.dozen12 = ?12 and r.dozen13 = ?13 and r.dozen14 = ?14 and r.dozen15 = ?15 and r.concurse <> ?16";

	boolean exists(Integer primaryKey);

	Historic findByConcurse(Long concurse);

	@Query(value = "select p from Historic p")
	public List<Historic> findWithPageable(Pageable pageable);

	@Query(value = DOZENS)
	List<Historic> findHistoricWithDozens(Integer dozen1, Integer dozen2, Integer dozen3, Integer dozen4, Integer dozen5,
			Integer dozen6, Integer dozen7, Integer dozen8, Integer dozen9, Integer dozen10, Integer dozen11, Integer dozen12,
			Integer dozen13, Integer dozen14, Integer dozen15);

	@Query(value = DOZENS_NE_CONCURSE)
	List<Historic> findHistoricByDozensNEConcurse(Integer dozen1, Integer dozen2, Integer dozen3, Integer dozen4,
			Integer dozen5, Integer dozen6, Integer dozen7, Integer dozen8, Integer dozen9, Integer dozen10, Integer dozen11,
			Integer dozen12, Integer dozen13, Integer dozen14, Integer dozen15, Integer concurse);
}
