package com.luckybox.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.Historic;
import com.luckybox.domain.QHistoric;
import com.luckybox.dto.HistoricDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
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
		return from(qHistoric).where(qHistoric.dozen1.eq(dozen).or(qHistoric.dozen2.eq(dozen))
				.or(qHistoric.dozen3.eq(dozen)).or(qHistoric.dozen4.eq(dozen)).or(qHistoric.dozen5.eq(dozen))
				.or(qHistoric.dozen6.eq(dozen)).or(qHistoric.dozen7.eq(dozen)).or(qHistoric.dozen8.eq(dozen))
				.or(qHistoric.dozen9.eq(dozen)).or(qHistoric.dozen10.eq(dozen)).or(qHistoric.dozen11.eq(dozen))
				.or(qHistoric.dozen12.eq(dozen)).or(qHistoric.dozen13.eq(dozen)).or(qHistoric.dozen14.eq(dozen))
				.or(qHistoric.dozen15.eq(dozen))).select(qHistoric.concurse.max()).fetchFirst();
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
		return from(qHistoric).select(qHistoric.concurse.castToNum(Integer.class))
				.where(qHistoric.dozen1.eq(dozen).or(qHistoric.dozen2.eq(dozen)).or(qHistoric.dozen3.eq(dozen))
						.or(qHistoric.dozen4.eq(dozen)).or(qHistoric.dozen5.eq(dozen)).or(qHistoric.dozen6.eq(dozen))
						.or(qHistoric.dozen7.eq(dozen)).or(qHistoric.dozen8.eq(dozen)).or(qHistoric.dozen9.eq(dozen))
						.or(qHistoric.dozen10.eq(dozen)).or(qHistoric.dozen11.eq(dozen)).or(qHistoric.dozen12.eq(dozen))
						.or(qHistoric.dozen13.eq(dozen)).or(qHistoric.dozen14.eq(dozen))
						.or(qHistoric.dozen15.eq(dozen)))
				.orderBy(qHistoric.concurse.asc()).fetch();
	}

	public List<Historic> findHistoricByDozens(HistoricDTO historicDTO) {
		return from(qHistoric)
				.where(whereDozens(historicDTO))//
						.orderBy(qHistoric.concurse.asc()).fetch();
	}

	public List<Historic> findHistoricByDozensEQConcurse(HistoricDTO historicDTO) {
		return from(qHistoric)
				.where(whereDozens(historicDTO)
						.and(qHistoric.concurse.eq(historicDTO.getConcurse())))
						.orderBy(qHistoric.concurse.asc()).fetch();
	}
	
	public List<Historic> findHistoricByDozensNEConcurse(HistoricDTO historicDTO) {
		return from(qHistoric)
				.where(whereDozens(historicDTO)
						.and(qHistoric.concurse.ne(historicDTO.getConcurse())))
						.orderBy(qHistoric.concurse.asc()).fetch();
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
		queryFactory.update(qHistoric).where(qHistoric.concurse.eq(concurse)).set(qHistoric.alreadyDrawn, true)
				.execute();
	}
	
	private BooleanExpression whereDozens(HistoricDTO historicDTO) {
		return qHistoric.dozen1.eq(historicDTO.getDozen1())//
				.and(qHistoric.dozen2.eq(historicDTO.getDozen2()))//
				.and(qHistoric.dozen3.eq(historicDTO.getDozen3()))//
				.and(qHistoric.dozen4.eq(historicDTO.getDozen4()))//
				.and(qHistoric.dozen5.eq(historicDTO.getDozen5()))//
				.and(qHistoric.dozen6.eq(historicDTO.getDozen6()))//
				.and(qHistoric.dozen7.eq(historicDTO.getDozen7()))//
				.and(qHistoric.dozen8.eq(historicDTO.getDozen8()))//
				.and(qHistoric.dozen9.eq(historicDTO.getDozen9()))//
				.and(qHistoric.dozen10.eq(historicDTO.getDozen10()))//
				.and(qHistoric.dozen11.eq(historicDTO.getDozen11()))//
				.and(qHistoric.dozen12.eq(historicDTO.getDozen12()))//
				.and(qHistoric.dozen13.eq(historicDTO.getDozen13()))//
				.and(qHistoric.dozen14.eq(historicDTO.getDozen14()))//
				.and(qHistoric.dozen15.eq(historicDTO.getDozen15()));
	}

}
