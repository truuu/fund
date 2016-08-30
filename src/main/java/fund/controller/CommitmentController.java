package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import fund.dto.Commitment;
import fund.dto.CommitmentDetail;
import fund.mapper.CommitmentDetailMapper;
import fund.mapper.CommitmentMapper;

@Controller
public class CommitmentController {

	@Autowired CommitmentMapper commitmentMapper;
	@Autowired CommitmentDetailMapper commitmentDetailMapper;

	/*commitment list*/
	@RequestMapping(value="/user/commitment.do", method=RequestMethod.GET)  
	public String commitment(Model model) {       // sponsor랑 합치면 @RequestParam추가하기
		model.addAttribute("list", commitmentMapper.selectBySponsorID(6)); // 6 test
		return "user/commitment";
	}

	/*commitment insert*/
	@RequestMapping(value="/user/commitmentTest.do", method=RequestMethod.GET)  
	public String createTest(Model model) {  
		model.addAttribute("sponsorID", 6);
		//commitmentMapper.insert(commitment);
		//commitmentDetailMapper.insert(commitmentDetail);
		return "user/commitmentTest";
	}

	@RequestMapping(value="/user/commitmentTest.do", method=RequestMethod.POST)  
	public String createTest(Model model, CommitmentDetail commitmentDetail) {      
		//commitmentMapper.insert(commitment);
		commitmentDetailMapper.insert(commitmentDetail);
		return "user/commitment";
	}
	
	
	
	


}