package com.luckybox.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.Combination;
import com.luckybox.domain.Historic;
import com.luckybox.domain.QCombination;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class CombinationRepositoryImpl extends QueryDslRepositorySupport {
	QCombination qCombination = QCombination.combination;

	@PersistenceContext
	private EntityManager em;

	public CombinationRepositoryImpl() {
		super(Combination.class);
	}

	public Combination findCombinationWithHistoric(Historic historic) {
		return from(qCombination).where(qCombination.dozen1.eq(historic.getDozen1()),
				qCombination.dozen2.eq(historic.getDozen2()), qCombination.dozen3.eq(historic.getDozen3()),
				qCombination.dozen4.eq(historic.getDozen4()), qCombination.dozen5.eq(historic.getDozen5()),
				qCombination.dozen6.eq(historic.getDozen6()), qCombination.dozen7.eq(historic.getDozen7()),
				qCombination.dozen8.eq(historic.getDozen8()), qCombination.dozen9.eq(historic.getDozen9()),
				qCombination.dozen10.eq(historic.getDozen10()), qCombination.dozen11.eq(historic.getDozen11()),
				qCombination.dozen12.eq(historic.getDozen12()), qCombination.dozen13.eq(historic.getDozen13()),
				qCombination.dozen14.eq(historic.getDozen14()), qCombination.dozen15.eq(historic.getDozen15()))
				.fetchFirst();
	}

	@Transactional
	public void markWithDrawn(Long combinationId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		queryFactory.update(qCombination).where(qCombination.combinationId.eq(combinationId))
				.set(qCombination.alreadyDrawn, true).execute();
	}

}
