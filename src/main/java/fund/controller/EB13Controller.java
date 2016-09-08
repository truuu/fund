package fund.controller;

import java.util.List;

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
}
