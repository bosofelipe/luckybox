package com.luckybox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Component;

import com.luckybox.domain.DozenInfo;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QDozenInfo;
import com.luckybox.domain.QHistoric;

@Component
public class DozenInfoRepositoryImpl extends QueryDslRepositorySupport {
	private static final QHistoric qHistoric = QHistoric.historic;
	private static final QDozenInfo qDozenInfo = QDozenInfo.dozenInfo;

	public DozenInfoRepositoryImpl() {
		super(DozenInfo.class);
	}


	public Long getLastConcurse() {
		return from(qHistoric).select(qHistoric.concurse.max()).fetchFirst();
	}
	
	public DozenInfo findByDozenAndType(Integer number, LotteryType type) {
			return from(qDozenInfo).where(qDozenInfo.number.eq(number), qDozenInfo.type.eq(type)).fetchFirst();
	}

	public DozenInfo getDetailsNumberMoreLate(Boolean asc) {
		if (asc)
			return from(qDozenInfo).limit(1).orderBy(qDozenInfo.lastDrawNumber.desc()).select(qDozenInfo).fetchFirst();
		return from(qDozenInfo).limit(1).orderBy(qDozenInfo.lastDrawNumber.asc()).select(qDozenInfo).fetchFirst();
	}

	public DozenInfo getNumberMoreAndLessDraw(Boolean asc) {
		if (asc)
			return from(qDozenInfo).limit(1).orderBy(qDozenInfo.countDrawNumber.asc()).select(qDozenInfo).fetchFirst();
		return from(qDozenInfo).limit(1).orderBy(qDozenInfo.countDrawNumber.desc()).select(qDozenInfo).fetchFirst();
	}

	public List<DozenInfo> getNumbersMoreAndLessLate(Boolean asc) {
		if (asc)
			return from(qDozenInfo).limit(5).orderBy(qDozenInfo.lastDrawNumber.desc()).fetchResults().getResults();
		return from(qDozenInfo).limit(5).orderBy(qDozenInfo.lastDrawNumber.asc()).fetchResults().getResults();
	}
	
}
