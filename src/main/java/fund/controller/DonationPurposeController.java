package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fund.dto.Pagination;
import fund.mapper.DonationPurposeMapper;

@Controller
public class DonationPurposeController {

	@Autowired DonationPurposeMapper donationPurposeMapper;
	
	/*扁何格利格废*/
	@RequestMapping("/code/donationPurposeList.do")
	public String codeList(Model model,Pagination pagination) {
		pagination.setRecordCount(donationPurposeMapper.selectCount());
		model.addAttribute("list",donationPurposeMapper.selectPage(pagination));
		return "code/donationPurposeList";
	}
	
}
