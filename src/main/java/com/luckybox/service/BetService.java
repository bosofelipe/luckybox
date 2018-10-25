package com.luckybox.service;

import static com.google.common.collect.Lists.newArrayList;
import static com.luckybox.mapper.DozenMapper.toBet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.bet.rule.BetValidationChain;
import com.luckybox.bet.rule.RuleDTO;
import com.luckybox.domain.Bet;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.BetInfoDTO;
import com.luckybox.dto.BetMessageDTO;
import com.luckybox.dto.DozenDTO;
import com.luckybox.dto.GroupBetMessageDTO;
import com.luckybox.exception.BetException;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.reader.CSVBetReader;
import com.luckybox.repository.BetRepository;
import com.luckybox.repository.BetRepositoryImpl;
import com.luckybox.repository.HistoricRepositoryImpl;

@Service
public class BetService {

	@Inject
	private BetRepository betRepository;

	@Inject
	private BetRepositoryImpl betRepositoryImpl;

	@Inject
	private HistoricRepositoryImpl historicRepository;

	@Inject
	private CSVBetReader reader;

	@Inject
	private BetValidationChain chainOfRules;

	public List<Bet> save(DozenDTO dozenDTO) {
		List<Bet> bets = newArrayList();
		if(dozenDTO.getConcurses() > 8) {
			throw new BetException("Concurses should be less than 8 times");
		}
		if (dozenDTO.getConcurses() != null) {
			for (int i = 0; i < dozenDTO.getConcurses(); i++) {
				Bet bet = toBet(dozenDTO);
				bet.setConcurse(dozenDTO.getConcurse() + i);
				setHits(bet, bet.getType());
				bets.add(bet);
			}
			betRepository.saveAll(bets);
		}
		return bets;
	}

	public boolean isAlreadyDrawn(DozenDTO dozenDTO) {
		List<Historic> historic = historicRepository.findHistoricByDozens(dozenDTO);
		boolean isAlreadyDown = historic.isEmpty() ? false : true;
		dozenDTO.setAlreadyDrawn(isAlreadyDown);
		return isAlreadyDown;
	}
	
	public List<DozenDTO> isAlreadyDrawn(List<DozenDTO> dozens) {
		dozens.stream().forEach(e -> isAlreadyDrawn(e));
		return dozens;
	}

	public List<RuleDTO> checkRules(List<DozenDTO> dozens, LotteryType type) {
		return chainOfRules.validationChain(dozens, type);
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
		List<RuleDTO> validationChain = checkRules(Arrays.asList(dozenDTO), dozenDTO.getType());
		Bet bet = DozenMapper.toBet(dozenDTO);
		if (validationChain.isEmpty())
			betRepository.save(bet);
		message.add(BetMessageDTO.builder().bet(bet).rules(validationChain).build());
	}

	public List<BetInfoDTO> checkBets(String type) {
		List<BetInfoDTO> betsDTO = Lists.newArrayList();
		betRepositoryImpl.findAllNotChecked()
							.forEach(bet -> check(betsDTO, bet, LotteryType.valueOf(type.toUpperCase())));
		return betsDTO;
	}

	private void check(List<BetInfoDTO> bets , Bet bet, LotteryType type) {
		Long concurse = bet.getConcurse();
		Historic historic = historicRepository.getHistoryByConcurseAndType(concurse, type);
		if (historic != null) {
			List<Integer> betDozens = DozenMapper.toList(bet);
			List<Integer> historicDozens = DozenMapper.toList(historic);
			Collections.sort(historicDozens);
			betDozens.retainAll(historicDozens);
			bet.setHits(betDozens.size());
			
			BetInfoDTO infoDTO = BetInfoDTO.builder().hits(betDozens.size())
											.concurse(concurse)
											.concurseDate(historic.getConcurseDate())
											.concurseDozens(historicDozens)
											.dozenDrawn(betDozens).build();
			
			bets.add(infoDTO);
			betRepository.save(bet);
		}
	}
	
	private void setHits(Bet bet, LotteryType type) {
		Long concurse = bet.getConcurse();
		Historic historic = historicRepository.getHistoryByConcurseAndType(concurse, type);
		if (historic != null) {
			List<Integer> betDozens = DozenMapper.toList(bet);
			List<Integer> historicDozens = DozenMapper.toList(historic);
			Collections.sort(historicDozens);
			betDozens.retainAll(historicDozens);
			bet.setHits(betDozens.size());
		}
	}
}
