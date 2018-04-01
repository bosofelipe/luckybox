package com.luckybox.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.Historic;
import com.luckybox.domain.QBet;

@Component
public class BetRepositoryImpl extends QueryDslRepositorySupport {
	private static final QBet qBet = QBet.bet;

	@PersistenceContext
	private EntityManager entityManager;

	public BetRepositoryImpl() {
		super(Historic.class);
	}

	public List<Bet> findAllNotChecked() {
		return from(qBet)
				.where(qBet.alreadyChecked.isFalse().or(qBet.alreadyChecked.isNull())).fetch();
	}

}
