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
	public String selectEB13(@RequestParam("commitmentDetailID") int[] commitmentDetailID,Model model){
		eb13Mapper.createEB13file();
		for(int i=0 ; i<commitmentDetailID.length; ++i){
			eb13Mapper.createEB13list(commitmentDetailID[i]);
		}
		//텍스트파일로 selectEB13에서 보여줬던 목록 만들어 내보내기.(파일 다운로드) 
		
		return "finance/eb13";
	}
	
	@RequestMapping(value="/finance/download.do", method=RequestMethod.GET)
	public void download() throws IOException { 
			List<EB13_CommitmentDetail> eb13List=eb13_commitmentDetailMapper.selectEB13();
	        EB13CreateFile.eb13CreateFile("EB13"+GETDATE_MMDD()+".txt", eb13List);
	        System.out.println(eb13List.size());
	}//eb13파일 다운로드
	
	private String GETDATE_MMDD() {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String date = String.valueOf(calendar.get(Calendar.DATE));
		
		return year+""+month+""+date;
	}
	
	@RequestMapping(value="/finance/eb14.do", method=RequestMethod.GET)
	public String eb14(Model model) {
		return "finance/eb14";
	}
	@RequestMapping(value="/finance/resultEB1314.do", method=RequestMethod.GET)
	public String resultEB1314(Model model) {
		List<EB13_CommitmentDetail> eb1314result = eb13_commitmentDetailMapper.selectEB1314();
		model.addAttribute("eb1314List", eb1314result);
		return "finance/resultEB1314";
	}
}
