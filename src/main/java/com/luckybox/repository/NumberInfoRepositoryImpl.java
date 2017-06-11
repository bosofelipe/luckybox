package com.luckybox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.NumberInfo;
import com.luckybox.domain.QHistoric;
import com.luckybox.domain.QNumberInfo;

@Component
public class NumberInfoRepositoryImpl extends QueryDslRepositorySupport {
	private static final QHistoric qHistoric = QHistoric.historic;
	private static final QNumberInfo qNumberInfo = QNumberInfo.numberInfo;

	public NumberInfoRepositoryImpl() {
		super(NumberInfo.class);
	}


	public Long getLastConcurse() {
		return from(qHistoric).select(qHistoric.concurse.max()).fetchFirst();
	}

	public NumberInfo getDetailsNumberMoreLate(Boolean asc) {
		if (asc)
			return from(qNumberInfo).limit(1).orderBy(qNumberInfo.lastDrawNumber.desc()).select(qNumberInfo).fetchFirst();
		return from(qNumberInfo).limit(1).orderBy(qNumberInfo.lastDrawNumber.asc()).select(qNumberInfo).fetchFirst();
	}

	public NumberInfo getNumberMoreAndLessDraw(Boolean asc) {
		if (asc)
			return from(qNumberInfo).limit(1).orderBy(qNumberInfo.countDrawNumber.asc()).select(qNumberInfo).fetchFirst();
		return from(qNumberInfo).limit(1).orderBy(qNumberInfo.countDrawNumber.desc()).select(qNumberInfo).fetchFirst();
	}

	public List<NumberInfo> getNumbersMoreAndLessLate(Boolean asc) {
		if (asc)
			return from(qNumberInfo).limit(5).orderBy(qNumberInfo.lastDrawNumber.desc()).fetchResults().getResults();
		return from(qNumberInfo).limit(5).orderBy(qNumberInfo.lastDrawNumber.asc()).fetchResults().getResults();
	}
	
}
