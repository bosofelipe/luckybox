package com.luckybox.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoric;
import com.luckybox.domain.QHistoricDataset;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class HistoricDatasetRepositoryImpl extends QuerydslRepositorySupport {
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
	
	public Integer getMaxSum(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.dozenSum.max(): qHistoricDataset.dozenSum.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxPrime(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.prime.max(): qHistoricDataset.prime.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxFibonacci(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.fibonacci.max() : qHistoricDataset.fibonacci.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxFibonacciPrime(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.fibonacciPrime.max() : qHistoricDataset.fibonacciPrime.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxDozensLastRaffle(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.dozensLastRaffle.max(): qHistoricDataset.dozensLastRaffle.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxGreatherSequence(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.greatherSequence.max() : qHistoricDataset.greatherSequence.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxPair(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.pair.max() : qHistoricDataset.pair.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
	public Integer getMaxSequence(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.qtdSequences.max() : qHistoricDataset.qtdSequences.min()).where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}
	
}