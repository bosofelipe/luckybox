package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.luckybox.domain.BetRuleSettings;
import com.luckybox.domain.LotteryType;

@Component
public interface BetRuleSettingsRepository  extends JpaRepository<BetRuleSettings, Long>{

	BetRuleSettings findByType(LotteryType type);
}
