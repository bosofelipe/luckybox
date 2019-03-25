package com.luckybox.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.LineColumnDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoric;
import com.luckybox.domain.QLineColumnDataset;

@Component
public class LineColumnDatasetRepositoryImpl extends QuerydslRepositorySupport {
	QHistoric qHistoric = QHistoric.historic;
	QLineColumnDataset qLineColumnDataset = QLineColumnDataset.lineColumnDataset;

	@PersistenceContext
	private EntityManager entityManager;

	public LineColumnDatasetRepositoryImpl() {
		super(LineColumnDataset.class);
	}

	public LineColumnDataset getHistoryByConcurseAndType(Long concurse, LotteryType lotteryType) {
		return from(qLineColumnDataset)
				.where(qLineColumnDataset.concurse.eq(concurse), qLineColumnDataset.type.eq(lotteryType)).fetchFirst();
	}
}