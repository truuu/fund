package fund.controller;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.dto.EB21_commitmentDetail;
import fund.mapper.EB21Mapper;
import fund.mapper.EB21_CommitmentDetailMapper;

@Controller
public class EB21Controller {
	@Autowired EB21Mapper eb21Mapper;
	@Autowired EB21_CommitmentDetailMapper eb21_commitmentDetailMapper;
	
	@RequestMapping(value="/finance/eb21.do", method=RequestMethod.GET)
	public String eb21(Model model) {
		return "finance/eb21";
	}

	@RequestMapping(value="/finance/eb21.do", method=RequestMethod.POST, params="cmd=selectEB21List")
	public String selectEB21(@RequestParam("paymentDay") int pDay,Model model){
		List<EB21_commitmentDetail> eb21List = eb21_commitmentDetailMapper.selectEB21(pDay);
		System.out.println(eb21List.size());
		model.addAttribute("eb21List", eb21List);
		return "finance/eb21";
	}
	@RequestMapping(value="/finance/eb21.do", method=RequestMethod.POST, params="cmd=createEB21file")
	public String createEB21file(@RequestParam("paymentDay") int pDay,@RequestParam("commitmentDetailID") int[] commitmentDetailID,Model model){
		Date paymentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-");
		eb21Mapper.createEB21file(sdf.format(paymentDate)+pDay);
		
		eb21Mapper.createEB21List(commitmentDetailID);
		return "finance/eb21";
	}
	
	@RequestMapping(value="/finance/eb22.do", method=RequestMethod.GET)
	public String eb22(Model model) {
		return "finance/eb22";
	}
	
	@RequestMapping(value="/finance/resultEB2122.do", method=RequestMethod.GET)
	public String resultEB2122(Model model) {
		List<EB21_commitmentDetail> eb2122List = eb21_commitmentDetailMapper.selectEB2122();
		model.addAttribute("eb2122List", eb2122List);
		return "finance/resultEB2122";
	}
}
