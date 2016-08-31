package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fund.dto.Pagination;
import fund.mapper.PrintDonationMapper;
import fund.mapper.PrintScholarshipMapper;

@Controller
public class PrintCertificateController {
	
	@Autowired PrintDonationMapper printDonationMapper;
	@Autowired PrintScholarshipMapper printScholarshipMapper;
	
	@RequestMapping("/certificate/printDonation_list.do")
    public String donationList(Model model, Pagination pagination) {
        model.addAttribute("list", printDonationMapper.selectPage(pagination));
        return "certificate/printDonation_list";
    }
	
	@RequestMapping("/certificate/printScholarship_list.do")
    public String scholarshipList(Model model, Pagination pagination) {
        model.addAttribute("list", printScholarshipMapper.selectPage(pagination));
        return "certificate/printScholarship_list";
    }
}
