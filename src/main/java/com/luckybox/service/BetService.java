package com.luckybox.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.bet.rule.BetValidationChain;
import com.luckybox.bet.rule.RuleDTO;
import com.luckybox.domain.Bet;
import com.luckybox.domain.Historic;
import com.luckybox.dto.BetMessageDTO;
import com.luckybox.dto.DozenDTO;
import com.luckybox.dto.GroupBetMessageDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.reader.CSVBetReader;
import com.luckybox.repository.BetRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Service
public class BetService {

	@Inject
	private BetRepository betRepository;

	@Inject
	private HistoricRepositoryImpl historicRepository;

	@Inject
	private CSVBetReader reader;

	@Inject
	private BetValidationChain chainOfRules;

	public Bet save(DozenDTO dozenDTO) {
		Bet bet = DozenMapper.toBet(dozenDTO);
		return betRepository.save(bet);
	}

	public boolean isAlreadyDrawn(DozenDTO dozenDTO) {
		List<Historic> historic = historicRepository.findHistoricByDozens(dozenDTO);
		return historic.isEmpty() ? false : true;
	}

	public List<RuleDTO> checkRules(DozenDTO dozenDTO) {
		return chainOfRules.validationChain(dozenDTO);
	}

	public GroupBetMessageDTO saveBetsByFile(File file) throws IOException {
		GroupBetMessageDTO groupMessage = new GroupBetMessageDTO();
		List<BetMessageDTO> message = Lists.newArrayList();
		List<DozenDTO> bets = reader.read(file);
		bets.stream().forEach(dozenDTO -> checkRulesAndSave(message, dozenDTO));
		groupMessage.setMessage(message);
		return groupMessage;
	}
	
	public GroupBetMessageDTO saveBetsByPath(String path) throws IOException {
		GroupBetMessageDTO groupMessage = new GroupBetMessageDTO();
		List<BetMessageDTO> message = Lists.newArrayList();
		List<DozenDTO> bets = reader.read(new File(path));
		bets.stream().forEach(dozenDTO -> checkRulesAndSave(message, dozenDTO));
		groupMessage.setMessage(message);
		return groupMessage;
	}

	private void checkRulesAndSave(List<BetMessageDTO> message, DozenDTO dozenDTO) {
		List<RuleDTO> validationChain = checkRules(dozenDTO);
		Bet bet = DozenMapper.toBet(dozenDTO);
		if (validationChain.isEmpty())
			betRepository.save(bet);
		message.add(BetMessageDTO.builder().bet(bet).rules(validationChain).build());
	}

}
