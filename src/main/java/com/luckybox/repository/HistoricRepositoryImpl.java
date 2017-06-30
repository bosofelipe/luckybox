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

	public Long findByNumber(int dozen) {
		return from(qHistoric)
				.where(qHistoric.dozen1.eq(dozen).or(qHistoric.dozen2.eq(dozen)).or(qHistoric.dozen3.eq(dozen))
						.or(qHistoric.dozen4.eq(dozen)).or(qHistoric.dozen5.eq(dozen)).or(qHistoric.dozen6.eq(dozen))
						.or(qHistoric.dozen7.eq(dozen)).or(qHistoric.dozen8.eq(dozen)).or(qHistoric.dozen9.eq(dozen))
						.or(qHistoric.dozen10.eq(dozen)).or(qHistoric.dozen11.eq(dozen))
						.or(qHistoric.dozen12.eq(dozen)).or(qHistoric.dozen13.eq(dozen))
						.or(qHistoric.dozen14.eq(dozen)).or(qHistoric.dozen15.eq(dozen)))
				.select(qHistoric.concurse.max()).fetchFirst();
	}

	public Long countNumberDraw(int dozen) {
		return from(qHistoric).where(qHistoric.dozen1.eq(dozen).or(qHistoric.dozen2.eq(dozen))
				.or(qHistoric.dozen3.eq(dozen)).or(qHistoric.dozen4.eq(dozen)).or(qHistoric.dozen5.eq(dozen))
				.or(qHistoric.dozen6.eq(dozen)).or(qHistoric.dozen7.eq(dozen)).or(qHistoric.dozen8.eq(dozen))
				.or(qHistoric.dozen9.eq(dozen)).or(qHistoric.dozen10.eq(dozen)).or(qHistoric.dozen11.eq(dozen))
				.or(qHistoric.dozen12.eq(dozen)).or(qHistoric.dozen13.eq(dozen)).or(qHistoric.dozen14.eq(dozen))
				.or(qHistoric.dozen15.eq(dozen))).fetchCount();
	}
	
	public List<Integer> listConcursesWithDozen(int dozen) {
		return from(qHistoric).select(qHistoric.concurse.castToNum(Integer.class)).where(qHistoric.dozen1.eq(dozen).or(qHistoric.dozen2.eq(dozen))
				.or(qHistoric.dozen3.eq(dozen)).or(qHistoric.dozen4.eq(dozen)).or(qHistoric.dozen5.eq(dozen))
				.or(qHistoric.dozen6.eq(dozen)).or(qHistoric.dozen7.eq(dozen)).or(qHistoric.dozen8.eq(dozen))
				.or(qHistoric.dozen9.eq(dozen)).or(qHistoric.dozen10.eq(dozen)).or(qHistoric.dozen11.eq(dozen))
				.or(qHistoric.dozen12.eq(dozen)).or(qHistoric.dozen13.eq(dozen)).or(qHistoric.dozen14.eq(dozen))
				.or(qHistoric.dozen15.eq(dozen))).orderBy(qHistoric.concurse.asc()).fetch();
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
