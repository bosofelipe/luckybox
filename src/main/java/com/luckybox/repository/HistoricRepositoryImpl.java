package com.luckybox.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoric;
import com.luckybox.dto.DozenDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class HistoricRepositoryImpl extends QuerydslRepositorySupport {
	private static final QHistoric qHistoric = QHistoric.historic;

	@PersistenceContext
	private EntityManager entityManager;

	public HistoricRepositoryImpl() {
		super(Historic.class);
	}

	public Long findByNumber(int dozen, LotteryType lotteryType) {
		return from(qHistoric).where(qHistoric.dozen1.eq(dozen).or(qHistoric.dozen2.eq(dozen))
				.or(qHistoric.dozen3.eq(dozen)).or(qHistoric.dozen4.eq(dozen)).or(qHistoric.dozen5.eq(dozen))
				.or(qHistoric.dozen6.eq(dozen)).or(qHistoric.dozen7.eq(dozen)).or(qHistoric.dozen8.eq(dozen))
				.or(qHistoric.dozen9.eq(dozen)).or(qHistoric.dozen10.eq(dozen)).or(qHistoric.dozen11.eq(dozen))
				.or(qHistoric.dozen12.eq(dozen)).or(qHistoric.dozen13.eq(dozen)).or(qHistoric.dozen14.eq(dozen))
				.or(qHistoric.dozen15.eq(dozen)).or(qHistoric.dozen16.eq(dozen)).or(qHistoric.dozen17.eq(dozen))
				.or(qHistoric.dozen18.eq(dozen)).or(qHistoric.dozen19.eq(dozen)).or(qHistoric.dozen20.eq(dozen))
				.and(qHistoric.type.eq(lotteryType))).select(qHistoric.concurse.max()).fetchFirst();
	}

	public Long countNumberDraw(int dozen, LotteryType lotteryType) {
		return from(qHistoric).where(qHistoric.dozen1.eq(dozen).or(qHistoric.dozen2.eq(dozen))
				.or(qHistoric.dozen3.eq(dozen)).or(qHistoric.dozen4.eq(dozen)).or(qHistoric.dozen5.eq(dozen))
				.or(qHistoric.dozen6.eq(dozen)).or(qHistoric.dozen7.eq(dozen)).or(qHistoric.dozen8.eq(dozen))
				.or(qHistoric.dozen9.eq(dozen)).or(qHistoric.dozen10.eq(dozen)).or(qHistoric.dozen11.eq(dozen))
				.or(qHistoric.dozen12.eq(dozen)).or(qHistoric.dozen13.eq(dozen)).or(qHistoric.dozen14.eq(dozen))
				.or(qHistoric.dozen15.eq(dozen)).or(qHistoric.dozen16.eq(dozen)).or(qHistoric.dozen17.eq(dozen))
				.or(qHistoric.dozen18.eq(dozen)).or(qHistoric.dozen19.eq(dozen)).or(qHistoric.dozen20.eq(dozen))
				.and(qHistoric.type.eq(lotteryType))).fetchCount();
	}

	public List<Integer> listConcursesWithDozen(int dozen, LotteryType lotteryType) {
		return from(qHistoric).select(qHistoric.concurse.castToNum(Integer.class))
				.where(qHistoric.dozen1.eq(dozen).or(qHistoric.dozen2.eq(dozen)).or(qHistoric.dozen3.eq(dozen))
						.or(qHistoric.dozen4.eq(dozen)).or(qHistoric.dozen5.eq(dozen)).or(qHistoric.dozen6.eq(dozen))
						.or(qHistoric.dozen7.eq(dozen)).or(qHistoric.dozen8.eq(dozen)).or(qHistoric.dozen9.eq(dozen))
						.or(qHistoric.dozen10.eq(dozen)).or(qHistoric.dozen11.eq(dozen)).or(qHistoric.dozen12.eq(dozen))
						.or(qHistoric.dozen13.eq(dozen)).or(qHistoric.dozen14.eq(dozen)).or(qHistoric.dozen15.eq(dozen))
						.or(qHistoric.dozen16.eq(dozen)).or(qHistoric.dozen17.eq(dozen)).or(qHistoric.dozen18.eq(dozen))
						.or(qHistoric.dozen19.eq(dozen)).or(qHistoric.dozen20.eq(dozen))
						.and(qHistoric.type.eq(lotteryType)))
				.orderBy(qHistoric.concurse.asc()).fetch();
	}

	public List<Integer> listConcursesWithDozens(List<Integer> dozens, LotteryType lotteryType) {
		return from(qHistoric).select(qHistoric.concurse.castToNum(Integer.class)).where(qHistoric.dozen1.in(dozens)
				.and(qHistoric.dozen2.in(dozens)).and(qHistoric.dozen3.in(dozens)).and(qHistoric.dozen4.in(dozens))
				.and(qHistoric.dozen5.in(dozens)).and(qHistoric.dozen6.in(dozens)).and(qHistoric.dozen7.in(dozens))
				.and(qHistoric.dozen8.in(dozens)).and(qHistoric.dozen9.in(dozens)).and(qHistoric.dozen10.in(dozens))
				.and(qHistoric.dozen11.in(dozens)).and(qHistoric.dozen12.in(dozens)).and(qHistoric.dozen13.in(dozens))
				.and(qHistoric.dozen14.in(dozens)).and(qHistoric.dozen15.in(dozens)).and(qHistoric.dozen16.in(dozens))
				.and(qHistoric.dozen17.in(dozens)).and(qHistoric.dozen18.in(dozens)).and(qHistoric.dozen19.in(dozens))
				.and(qHistoric.dozen20.in(dozens)).and(qHistoric.type.eq(lotteryType)))
				.orderBy(qHistoric.concurse.asc()).fetch();
	}

	public List<Historic> findHistoricByDozens(DozenDTO dozenDTO) {
		return from(qHistoric).where(whereDozens(dozenDTO))//
				.orderBy(qHistoric.concurse.asc()).fetch();
	}

	public List<Historic> findHistoricByDozensEQConcurse(DozenDTO dozenDTO) {
		return from(qHistoric).where(whereDozens(dozenDTO).and(qHistoric.concurse.eq(dozenDTO.getConcurse())))
				.orderBy(qHistoric.concurse.asc()).fetch();
	}

	public List<Historic> findHistoricByDozensNEConcurse(DozenDTO dozenDTO) {
		return from(qHistoric).where(whereDozens(dozenDTO).and(qHistoric.concurse.ne(dozenDTO.getConcurse())))
				.orderBy(qHistoric.concurse.asc()).fetch();
	}

	public List<Historic> getLastRaffles(Integer range, LotteryType lotteryType) {
		Long lastIndexRaffle = getLastIndexRaffle(lotteryType);
		if (lastIndexRaffle != null)
			return from(qHistoric).where(qHistoric.concurse.goe(lastIndexRaffle - range))
					.orderBy(qHistoric.concurse.desc()).fetch();
		return Lists.newArrayList();
	}

	public Long getLastIndexRaffle(LotteryType lotteryType) {
		return from(qHistoric).where(qHistoric.type.eq(lotteryType)).select(qHistoric.concurse.max()).fetchFirst();
	}

	@Transactional
	public void updateAlreadyDrawn(Long concurse, LotteryType lotteryType) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		queryFactory.update(qHistoric).where(qHistoric.concurse.eq(concurse).and(qHistoric.type.eq(lotteryType)))
				.set(qHistoric.alreadyDrawn, false).execute();
	}

	public Historic getHistoryByConcurseAndType(Long concurse, LotteryType lotteryType) {
		return from(qHistoric).where(qHistoric.concurse.eq(concurse), qHistoric.type.eq(lotteryType)).fetchFirst();
	}

	public List<Historic> findAllByAlreadyDrawnIsNullAndType(LotteryType lotteryType) {
		return from(qHistoric).where(qHistoric.alreadyDrawn.isNull(), qHistoric.type.eq(lotteryType)).fetch();
	}

	private BooleanExpression whereDozens(DozenDTO dozenDTO) {
		if (LotteryType.LOTOFACIL == dozenDTO.getType()) {
			return qHistoric.dozen1.eq(dozenDTO.getDozen1())//
					.and(qHistoric.dozen2.eq(dozenDTO.getDozen2()))//
					.and(qHistoric.dozen3.eq(dozenDTO.getDozen3()))//
					.and(qHistoric.dozen4.eq(dozenDTO.getDozen4()))//
					.and(qHistoric.dozen5.eq(dozenDTO.getDozen5()))//
					.and(qHistoric.dozen6.eq(dozenDTO.getDozen6()))//
					.and(qHistoric.dozen7.eq(dozenDTO.getDozen7()))//
					.and(qHistoric.dozen8.eq(dozenDTO.getDozen8()))//
					.and(qHistoric.dozen9.eq(dozenDTO.getDozen9()))//
					.and(qHistoric.dozen10.eq(dozenDTO.getDozen10()))//
					.and(qHistoric.dozen11.eq(dozenDTO.getDozen11()))//
					.and(qHistoric.dozen12.eq(dozenDTO.getDozen12()))//
					.and(qHistoric.dozen13.eq(dozenDTO.getDozen13()))//
					.and(qHistoric.dozen14.eq(dozenDTO.getDozen14()))//
					.and(qHistoric.type.eq(dozenDTO.getType()));
		} else if (LotteryType.LOTOMANIA == dozenDTO.getType()) {
			return qHistoric.dozen1.eq(dozenDTO.getDozen1())//
					.and(qHistoric.dozen2.eq(dozenDTO.getDozen2()))//
					.and(qHistoric.dozen3.eq(dozenDTO.getDozen3()))//
					.and(qHistoric.dozen4.eq(dozenDTO.getDozen4()))//
					.and(qHistoric.dozen5.eq(dozenDTO.getDozen5()))//
					.and(qHistoric.dozen6.eq(dozenDTO.getDozen6()))//
					.and(qHistoric.dozen7.eq(dozenDTO.getDozen7()))//
					.and(qHistoric.dozen8.eq(dozenDTO.getDozen8()))//
					.and(qHistoric.dozen9.eq(dozenDTO.getDozen9()))//
					.and(qHistoric.dozen10.eq(dozenDTO.getDozen10()))//
					.and(qHistoric.dozen11.eq(dozenDTO.getDozen11()))//
					.and(qHistoric.dozen12.eq(dozenDTO.getDozen12()))//
					.and(qHistoric.dozen13.eq(dozenDTO.getDozen13()))//
					.and(qHistoric.dozen14.eq(dozenDTO.getDozen14()))//
					.and(qHistoric.dozen15.eq(dozenDTO.getDozen15()))//
					.and(qHistoric.dozen16.eq(dozenDTO.getDozen16()))//
					.and(qHistoric.dozen17.eq(dozenDTO.getDozen17()))//
					.and(qHistoric.dozen18.eq(dozenDTO.getDozen18()))//
					.and(qHistoric.dozen19.eq(dozenDTO.getDozen19()))//
					.and(qHistoric.dozen20.eq(dozenDTO.getDozen20()))//
					.and(qHistoric.type.eq(dozenDTO.getType()));
		} else {
			return qHistoric.dozen1.eq(dozenDTO.getDozen1())//
					.and(qHistoric.dozen2.eq(dozenDTO.getDozen2()))//
					.and(qHistoric.dozen3.eq(dozenDTO.getDozen3()))//
					.and(qHistoric.dozen4.eq(dozenDTO.getDozen4()))//
					.and(qHistoric.dozen5.eq(dozenDTO.getDozen5()))//
					.and(qHistoric.type.eq(dozenDTO.getType()));
		}
	}

}
