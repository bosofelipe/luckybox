package com.luckybox.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.CombinationDozens;
import com.luckybox.domain.Historic;
import com.luckybox.domain.QCombinationDozens;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class CombinationDozensRepositoryImpl extends QueryDslRepositorySupport {
	QCombinationDozens qCombination = QCombinationDozens.combinationDozens;

	@PersistenceContext
	private EntityManager em;

	public CombinationDozensRepositoryImpl() {
		super(CombinationDozens.class);
	}

	public CombinationDozens findCombinationWithHistoric(Historic historic) {
		return from(qCombination).where(qCombination.dozen1.eq(historic.getDozen1()),
				qCombination.dozen2.eq(historic.getDozen2()), qCombination.dozen3.eq(historic.getDozen3()),
				qCombination.dozen4.eq(historic.getDozen4()), qCombination.dozen5.eq(historic.getDozen5()),
				qCombination.dozen6.eq(historic.getDozen6()), qCombination.dozen7.eq(historic.getDozen7()),
				qCombination.dozen8.eq(historic.getDozen8()), qCombination.dozen9.eq(historic.getDozen9()),
				qCombination.dozen10.eq(historic.getDozen10()), qCombination.dozen11.eq(historic.getDozen11()),
				qCombination.dozen12.eq(historic.getDozen12()), qCombination.dozen13.eq(historic.getDozen13()),
				qCombination.dozen14.eq(historic.getDozen14()), qCombination.dozen15.eq(historic.getDozen15()),
				qCombination.alreadyDrawn.isNull())
				.fetchFirst();
	}

	public List<CombinationDozens> listCombinations(Long limit) {
		long maxConcurseDrawn = getMaxConcurseDrawn();
		long minConcurseDrawn = getMinConcurseDrawn();
		return from(qCombination)
				.where(qCombination.id.between(minConcurseDrawn, maxConcurseDrawn), qCombination.alreadyDrawn.isFalse())
				.limit(limit).fetch();
	}

	@Transactional
	public void markWithDrawn(Long combinationId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		queryFactory.update(qCombination).where(qCombination.id.eq(combinationId)).set(qCombination.alreadyDrawn, true)
				.execute();
	}

	public long getMaxConcurseSaved() {
		Long concurseId = from(qCombination).select(qCombination.id.max()).fetchOne();
		return concurseId == null ? 0 : concurseId;
	}

	private long getMaxConcurseDrawn() {
		return from(qCombination).select(qCombination.id.max()).where(qCombination.alreadyDrawn.isTrue()).fetchOne();
	}

	private long getMinConcurseDrawn() {
		return from(qCombination).select(qCombination.id.min()).where(qCombination.alreadyDrawn.isTrue()).fetchOne();
	}

}
