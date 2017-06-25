package com.luckybox.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.QHistoricDataset;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class HistoricDatasetRepositoryImpl extends QueryDslRepositorySupport {
	QHistoricDataset qHistoricDataset = QHistoricDataset.historicDataset;

	@PersistenceContext
	private EntityManager entityManager;

	public HistoricDatasetRepositoryImpl() {
		super(HistoricDataset.class);
	}

	@Transactional
	public void updateVariationAndDozensLastRaffle(HistoricDataset dataset) {
			JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
			queryFactory.update(qHistoricDataset).where(qHistoricDataset.concurse.eq(dataset.getConcurse()))
					.set(qHistoricDataset.variationSum, dataset.getVariationSum())
					.set(qHistoricDataset.dozensLastRaffle, dataset.getDozensLastRaffle())
					.execute();
	}
}