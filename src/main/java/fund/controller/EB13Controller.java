package fund.controller;

import java.io.BufferedOutputStream;
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

import fund.dto.EB13;
import fund.dto.EB13_CommitmentDetail;
import fund.mapper.EB13Mapper;
import fund.mapper.EB13_CommitmentDetailMapper;
import fund.service.WriteEB13TOTextFile;

@Controller
public class EB13Controller {
	@Autowired EB13Mapper eb13Mapper;
	@Autowired EB13_CommitmentDetailMapper eb13_commitmentDetailMapper;
	@Autowired WriteEB13TOTextFile writeEB13TOTextFile;
	
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
	public String selectEB13(@RequestParam("commitmentDetailID") int commitmentDetailID,Model model){
		eb13Mapper.createEB13file();
		eb13Mapper.createEB13list(commitmentDetailID);
		//텍스트파일로 selectEB13에서 보여줬던 목록 만들어 내보내기.(파일 다운로드) 
		
		return "finance/eb13";
	}
	
	@RequestMapping("/finance/download.pd")
	public void download(HttpServletResponse response) throws Exception { 
		eb13Mapper.createEB13file();
		List<EB13_CommitmentDetail> eb13List = eb13_commitmentDetailMapper.selectEB13();
		WriteEB13TOTextFile.writeEB13("EB13"+GETDATE(),eb13List);
		
	}//eb13파일 다운로드
	
	private String GETDATE() {
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String date = String.valueOf(calendar.get(Calendar.DATE));
		
		return year+""+month+""+date;
	}
}
