package com.luckybox.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoric;
import com.luckybox.domain.QHistoricDataset;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class HistoricDatasetRepositoryImpl extends QueryDslRepositorySupport {
	QHistoricDataset qHistoricDataset = QHistoricDataset.historicDataset;
	QHistoric qHistoric = QHistoric.historic;
	

	@PersistenceContext
	private EntityManager entityManager;

	public HistoricDatasetRepositoryImpl() {
		super(HistoricDataset.class);
	}

	@Transactional
	public void updateVariationAndDozensLastRaffle(HistoricDataset dataset, LotteryType type) {
			JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
			queryFactory.update(qHistoricDataset).where(
					qHistoricDataset.concurse.eq(dataset.getConcurse()),
					qHistoricDataset.type.eq(type))
					.set(qHistoricDataset.variationSum, dataset.getVariationSum())
					.set(qHistoricDataset.dozensLastRaffle, dataset.getDozensLastRaffle())
					.execute();
	}
	
	public HistoricDataset findByConcurseAndType(Long concurse, LotteryType type) {
		return from(qHistoricDataset)
			.where(qHistoricDataset.historic.concurse.eq(concurse), qHistoricDataset.historic.type.eq(type))//
			.orderBy(qHistoricDataset.concurse.asc()).fetchFirst();
	}
}