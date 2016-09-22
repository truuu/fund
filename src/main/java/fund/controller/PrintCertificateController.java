package fund.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fund.dto.Pagination;
import fund.dto.PrintDonationCreate;
import fund.dto.PrintScholarshipCreate;
import fund.mapper.PrintDonationMapper;
import fund.mapper.PrintScholarshipMapper;
import fund.service.PrintCertificateService;

@Controller
public class PrintCertificateController {
	
	@Autowired PrintDonationMapper printDonationMapper;
	@Autowired PrintScholarshipMapper printScholarshipMapper;
	
	@RequestMapping("/certificate/printDonation_list.do")
    public String donationList(Model model, Pagination pagination) {
		pagination.setRecordCount(printDonationMapper.selectCount(pagination));
        model.addAttribute("list", printDonationMapper.selectPage(pagination));
        return "certificate/printDonation_list";
    }
	
	@RequestMapping("/certificate/printScholarship_list.do")
    public String scholarshipList(Model model, Pagination pagination) {
		pagination.setRecordCount(printScholarshipMapper.selectCount(pagination));
        model.addAttribute("list", printScholarshipMapper.selectPage(pagination));
        return "certificate/printScholarship_list";
    }
	
	@RequestMapping(value="/certificate/printScholarship.do", method=RequestMethod.GET)
    public String printScholarship(Model model) {
		model.addAttribute("serialNo",printScholarshipMapper.selectSerialNum());
        return "certificate/printScholarship";
    }
	
	@RequestMapping(value="/certificate/printScholarship.do", method=RequestMethod.POST)
    public String printScholarship(Model model,PrintScholarshipCreate printScholarshipCreate) {
		printScholarshipCreate.setDate(PrintCertificateService.printDate());
		String serialNum="제 "+printScholarshipCreate.getSerialNo()+" 호";
		printScholarshipCreate.setSerialNo(serialNum);
		printScholarshipCreate.setTitle("장학증서");
		printScholarshipCreate.setUniversity("성 공 회 대 학 교  총장 이정구");
		printScholarshipCreate.setTopCourse("최고경영자과정 총동문회장 이평구");
		//insert 하기 printScholarshipMapper에!
		model.addAttribute("scholarship",printScholarshipCreate);
        return "certificate/scholarshipReportContent";
    }
	
	@RequestMapping(value="/certificate/printDonation.do", method=RequestMethod.GET)
    public String printDonation(Model model) {
		//int number=printDonationMapper.selectSerialNo();
		//int year1 = Calendar.getInstance().get(Calendar.YEAR); // 현재 년도
		//String serial=Integer.toString(number);
		//String year2=Integer.toString(year1);
		
		//String serialNo="2016-000"+serial;
		model.addAttribute("serialNo",printDonationMapper.selectSerialNum());
        return "certificate/printDonation";
    }
	
	@RequestMapping(value="/certificate/printDonation.do", method=RequestMethod.POST)
    public String printDonation(Model model,PrintDonationCreate printDonationCreate) {
		printDonationCreate.setDate(PrintCertificateService.printDate());
		String serialNum="제 "+printDonationCreate.getSerialNo()+" 호";
		printDonationCreate.setSerialNo(serialNum);
		printDonationCreate.setTitle("기부증서");
		printDonationCreate.setUniversity("성공회대학교 총장");
		printDonationCreate.setPresident("이정구");
		//미리보기,인쇄 버튼 which? printDonationMapper INSERT 추가하기
		
		model.addAttribute("donation",printDonationCreate);
		return "certificate/donationReportContent";
	}
	
	@RequestMapping(value="/certificate/donationRepoertContent.do")
	public String donationReportContent(Model model){
		
		return "certificate/donationReportContent";
	}
	
	@RequestMapping(value="/certificate/scholarshipRepoertContent.do")
	public String scholarshipReportContent(Model model){
		
		return "certificate/scholarshipReportContent";
	}
	
	@RequestMapping(value="/certificate/donationDelete.do")
	public String donationDelete(Model model,@RequestParam(value="checkArray[]") List<Integer> params){

		for(int i=0 ; i<params.size(); i++){
			System.out.println(params.get(i));
			printDonationMapper.delete(params.get(i));
		}
		
		return "redirect:/certificate/printDonation_list.do";
	}
	
	@RequestMapping(value="/certificate/scholarshipDelete.do", method=RequestMethod.GET)
	public String scholarshipDelete(Model model,@RequestParam(value="checkArray[]") List<Integer> params){

		for(int i=0 ; i<params.size(); i++){
			System.out.println(params.get(i));
			printScholarshipMapper.delete(params.get(i));
		}
		return "redirect:/certificate/printScholarship_list.do";
	}
}
	
