package com.luckybox.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.Historic;
import com.luckybox.domain.QHistoric;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class HistoricRepositoryImpl extends QueryDslRepositorySupport {
	private static final QHistoric qHistoric = QHistoric.historic;

	@PersistenceContext
	private EntityManager entityManager;

	public HistoricRepositoryImpl() {
		super(Historic.class);
	}

	public Long findByNumber(int number) {
		return from(qHistoric)
				.where(qHistoric.dozen1.eq(number).or(qHistoric.dozen2.eq(number)).or(qHistoric.dozen3.eq(number))
						.or(qHistoric.dozen4.eq(number)).or(qHistoric.dozen5.eq(number)).or(qHistoric.dozen6.eq(number))
						.or(qHistoric.dozen7.eq(number)).or(qHistoric.dozen8.eq(number)).or(qHistoric.dozen9.eq(number))
						.or(qHistoric.dozen10.eq(number)).or(qHistoric.dozen11.eq(number))
						.or(qHistoric.dozen12.eq(number)).or(qHistoric.dozen13.eq(number))
						.or(qHistoric.dozen14.eq(number)).or(qHistoric.dozen15.eq(number)))
				.select(qHistoric.concurse.max()).fetchFirst();
	}

	public Long countNumberDraw(int number) {
		return from(qHistoric).where(qHistoric.dozen1.eq(number).or(qHistoric.dozen2.eq(number))
				.or(qHistoric.dozen3.eq(number)).or(qHistoric.dozen4.eq(number)).or(qHistoric.dozen5.eq(number))
				.or(qHistoric.dozen6.eq(number)).or(qHistoric.dozen7.eq(number)).or(qHistoric.dozen8.eq(number))
				.or(qHistoric.dozen9.eq(number)).or(qHistoric.dozen10.eq(number)).or(qHistoric.dozen11.eq(number))
				.or(qHistoric.dozen12.eq(number)).or(qHistoric.dozen13.eq(number)).or(qHistoric.dozen14.eq(number))
				.or(qHistoric.dozen15.eq(number))).fetchCount();
	}

	public List<Historic> getLastRaffles(Integer range) {
		Long lastIndexRaffle = getLastIndexRaffle();
		return from(qHistoric).where(qHistoric.concurse.goe(lastIndexRaffle - range)).fetch();
	}

	public Long getLastIndexRaffle() {
		return from(qHistoric).select(qHistoric.concurse.max()).fetchFirst();
	}

	@Transactional
	public void updateAlreadyDrawn(Long concurse) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.update(qHistoric).where(qHistoric.concurse.eq(concurse))
				.set(qHistoric.alreadyDrawn, true).execute();
	}

}
