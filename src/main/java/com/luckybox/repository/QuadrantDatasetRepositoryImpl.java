package com.luckybox.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoric;
import com.luckybox.domain.QQuadrantDataset;
import com.luckybox.domain.QuadrantDataset;

@Component
public class QuadrantDatasetRepositoryImpl extends QuerydslRepositorySupport {
	QHistoric qHistoric = QHistoric.historic;
	QQuadrantDataset qQuadrantDataset = QQuadrantDataset.quadrantDataset;

	@PersistenceContext
	private EntityManager entityManager;

	public QuadrantDatasetRepositoryImpl() {
		super(QuadrantDataset.class);
	}

	public QuadrantDataset getHistoryByConcurseAndType(Long concurse, LotteryType lotteryType) {
		return from(qQuadrantDataset)
				.where(qQuadrantDataset.concurse.eq(concurse), qQuadrantDataset.type.eq(lotteryType)).fetchFirst();
	}
}