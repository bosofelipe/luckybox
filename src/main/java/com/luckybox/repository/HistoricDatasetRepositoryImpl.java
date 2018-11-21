package com.luckybox.repository;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoric;
import com.luckybox.domain.QHistoricDataset;
import com.luckybox.dto.HistoricDataSetDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
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
		queryFactory.update(qHistoricDataset)
				.where(qHistoricDataset.concurse.eq(dataset.getConcurse()), qHistoricDataset.type.eq(type))
				.set(qHistoricDataset.variationSum, dataset.getVariationSum())
				.set(qHistoricDataset.dozensLastRaffle, dataset.getDozensLastRaffle()).execute();
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
		return from(qHistoricDataset).select(max ? qHistoricDataset.dozenSum.max() : qHistoricDataset.dozenSum.min())
				.where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}

	public Integer getMaxPrime(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.prime.max() : qHistoricDataset.prime.min())
				.where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}

	public Integer getMaxFibonacci(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.fibonacci.max() : qHistoricDataset.fibonacci.min())
				.where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}

	public Integer getMaxFibonacciPrime(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset)
				.select(max ? qHistoricDataset.fibonacciPrime.max() : qHistoricDataset.fibonacciPrime.min())
				.where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}

	public Integer getMaxDozensLastRaffle(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset)
				.select(max ? qHistoricDataset.dozensLastRaffle.max() : qHistoricDataset.dozensLastRaffle.min())
				.where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}

	public Integer getMaxGreatherSequence(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset)
				.select(max ? qHistoricDataset.greatherSequence.max() : qHistoricDataset.greatherSequence.min())
				.where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}

	public Integer getMaxPair(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset).select(max ? qHistoricDataset.pair.max() : qHistoricDataset.pair.min())
				.where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}

	public Integer getMaxSequence(LotteryType lotteryType, Boolean max) {
		return from(qHistoricDataset)
				.select(max ? qHistoricDataset.qtdSequences.max() : qHistoricDataset.qtdSequences.min())
				.where(qHistoricDataset.type.eq(lotteryType)).fetchOne();
	}

	public List<HistoricDataSetDTO> countFieldByValue(LotteryType lotteryType, NumberPath<Integer> field) {
		List<HistoricDataSetDTO> dataSet = from(qHistoricDataset).where(qHistoricDataset.type.eq(lotteryType)).groupBy(field)
				.select(Projections.constructor(HistoricDataSetDTO.class, field, qHistoricDataset.count())).fetch();

		dataSet.stream().forEach(dataSetDTO -> setLastConcurseAndDiff(lotteryType, dataSetDTO, field));

		dataSet.sort(Comparator.comparing(HistoricDataSetDTO::getValue));
		
		return dataSet;
	}

	private void setLastConcurseAndDiff(LotteryType lotteryType, HistoricDataSetDTO dataSetDTO, NumberPath<Integer> field) {
		dataSetDTO.setLastConcurse(from(qHistoricDataset).select(qHistoricDataset.concurse.max())
				.where(field.eq(dataSetDTO.getValue()), qHistoricDataset.type.eq(lotteryType)).fetchOne());
		setMaxDiff(lotteryType, dataSetDTO, field);
	}

	private void setMaxDiff(LotteryType lotteryType, HistoricDataSetDTO dataSetDTO, NumberPath<Integer> field) {
		List<HistoricDataSetDTO> results = from(qHistoricDataset)
				.select(Projections.constructor(HistoricDataSetDTO.class, field, qHistoricDataset.concurse))
				.where(field.eq(dataSetDTO.getValue()), qHistoricDataset.type.eq(lotteryType)).orderBy(qHistoricDataset.concurse.asc()).fetch();
		Long diff = 0L;
		for (int i = 0; i < results.size(); i++) {
			if (i != 0 && results.size() > i + 2) {
				Long value = results.get(i + 1).getCount();
				Long previousValue = results.get(i).getCount();
				Long diffBetweenConcurse = value - previousValue;

				if (diffBetweenConcurse > diff)
					diff = diffBetweenConcurse;
			}
		}
		dataSetDTO.setMaxDiffBetweenConcurses(diff);
	}
}