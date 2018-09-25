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
	
	public HistoricDataset getHistoryByConcurseAndType(Long concurse, LotteryType lotteryType) {
		return from(qHistoricDataset)
				.where(qHistoricDataset.concurse.eq(concurse), qHistoricDataset.type.eq(lotteryType)).fetchFirst();
	}
	
	public Integer getMinSum(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.dozenSum.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxSum(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.dozenSum.max()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxPrime(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.prime.max()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxFibonacci(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.fibonacci.max()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxFibonacciPrime(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.fibonacciPrime.max()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxDozensLastRaffle(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.dozensLastRaffle.max()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxGreatherSequence(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.greatherSequence.max()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxPair(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.pair.max()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxSequence(LotteryType lotteryType) {
		return from(qHistoricDataset).select(qHistoricDataset.qtdSequences.max()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
}