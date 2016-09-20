package fund.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fund.dto.EB13_CommitmentDetail;
import fund.dto.EB14;
import fund.mapper.EB13Mapper;
import fund.mapper.EB13_CommitmentDetailMapper;


@Controller
public class EB13Controller {
	@Autowired EB13Mapper eb13Mapper;
	@Autowired EB13_CommitmentDetailMapper eb13_commitmentDetailMapper;
	
	@RequestMapping(value="/finance/eb13.do", method=RequestMethod.GET)
	public String eb13(Model model) {
		return "finance/eb13";
	}
	@RequestMapping(value="/finance/eb13.do", method=RequestMethod.POST, params="cmd=selectEB13")
	public String selectEB13(Model model){
		List<EB13_CommitmentDetail> eb13List = eb13_commitmentDetailMapper.selectEB13();
		model.addAttribute("eb13List", eb13List);
		return "finance/eb13";
	}
	@RequestMapping(value="/finance/eb13.do", method=RequestMethod.POST, params="cmd=createEB13file")
	public String createEB13file(@RequestParam("commitmentDetailID") int[] commitmentDetailID,Model model) throws IOException{
		List<EB13_CommitmentDetail> eb13List = eb13_commitmentDetailMapper.selectEB13();
		model.addAttribute("eb13List", eb13List);
		CreateEB13File.createEB13File(eb13List);
		
		eb13Mapper.createEB13file();
		for(int i=0 ; i<commitmentDetailID.length; ++i){
			eb13Mapper.createEB13list(commitmentDetailID[i]);
		}
		
		return "finance/eb13";
	}
	
	@RequestMapping(value="/finance/eb14.do", method=RequestMethod.GET)
	public String eb14(Model model) {
		return "finance/eb14";
	}
	@RequestMapping(value="/finance/eb14.do", method=RequestMethod.POST, params="cmd=selectEB14")
	public String selectEB14(Model model) {
		ArrayList<String> eb14file = ReadEB14File.readEB14File();
		EB14 eb14 = new EB14();
		for(int i=0;i<eb14file.size();){
			String getList = eb14file.get(i);
			String sub = getList.substring(25);
			String[] eb14Values = sub.split("");
			eb14.setSponsorNo(eb14Values[i]);
			eb14.setJumin(eb14Values[i+1]);
			eb14.setBankCode(eb14Values[i+2]);
			eb14.setAccountNo(eb14Values[i+3]);
			i=i+4;
		}
		List<EB14> eb14List = (List<EB14>) eb14;
		model.addAttribute("eb14List",eb14List);
		
		return "finance/eb14";
	}
	@RequestMapping(value="/finance/resultEB1314.do", method=RequestMethod.GET)
	public String resultEB1314(Model model) {
		List<EB13_CommitmentDetail> eb1314result = eb13_commitmentDetailMapper.selectEB1314();
		model.addAttribute("eb1314List", eb1314result);
		return "finance/resultEB1314";
	}
}
