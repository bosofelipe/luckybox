package com.luckybox.web.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.ApiPageable;
import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.BetDTO;
import com.luckybox.dto.BetInfoDTO;
import com.luckybox.dto.DozenDTO;
import com.luckybox.dto.GroupBetMessageDTO;
import com.luckybox.dto.HitsDTO;
import com.luckybox.repository.BetRepository;
import com.luckybox.service.BetService;
import com.luckybox.service.HistoricService;

import io.swagger.annotations.ApiOperation;
import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/bet")
@Produces(value=javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(value=javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class BetResource {

	@Inject
	private BetService betService;
	
	@Inject
	private BetRepository betRepository;
	
	@Inject
	private HistoricService historicService;

	@ApiOperation(value="save a list of bets", notes="")
	@PostMapping(path = "/toBet", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<Bet>> toBet(@RequestBody DozenDTO dozenDTO) {
		return new ResponseEntity<List<Bet>>(betService.save(dozenDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value="Validate bets by rules defined", notes="")
	@PostMapping(path = "/validateAll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<DozenDTO>> validateBets(@RequestBody List<DozenDTO> dozens) {
		return new ResponseEntity<List<DozenDTO>>(betService.isAlreadyDrawn(dozens), HttpStatus.OK);
	}
	
	@ApiOperation(value="Check if bets are inside rules", notes="")
	@PostMapping(path = "/checkRules", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<BetRule>> checkRules(@RequestBody DozenDTO dozenDTO) {
		return new ResponseEntity<List<BetRule>>(betService.checkRules(dozenDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value="Check if bets are inside rules by type of lottery", notes="")
	@GetMapping(path = "/check/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<BetInfoDTO> checkBets(@PathVariable String type) throws IOException, ZipException {
		return betService.checkBets(type);
	}
	
	@ApiPageable
	@ApiOperation(value="List paginated concurses by type of lottery", notes="")
	@GetMapping(path = "/list/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public Page<Bet> listByType(@PathVariable String type,Pageable pageable) throws IOException, ZipException {
		return betRepository.findAllByType(LotteryType.valueOf(type.toUpperCase()),pageable);
	}

	/*@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String name = "file";
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name + "-uploaded")));
				stream.write(bytes);
				stream.close();
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	@ApiOperation(value="Save bets using file path", notes="")
	@RequestMapping(value = "/saveByPath", method = RequestMethod.POST)
	public GroupBetMessageDTO saveBetsByPath(@RequestParam("file") String path) throws IOException {
		return betService.saveBetsByPath(path);
	}
	
	@ApiOperation(value="Check if bets are inside rules", notes="")
	@PostMapping(path = "/listHistByConcurse", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<HitsDTO>> listHistByConcurse(@RequestBody BetDTO betDTO) {
		return new ResponseEntity<List<HitsDTO>>(historicService.listHistByConcurse(betDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value="Check if bets are inside rules", notes="")
	@PostMapping(path = "/listHistByConcurse/grouped", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Map<Integer, List<Long>>> listHistByConcurseGrouped(@RequestBody BetDTO betDTO) {
		return new ResponseEntity<Map<Integer, List<Long>>>(historicService.listGroupedHistByConcurse(betDTO), HttpStatus.OK);
	}
}
