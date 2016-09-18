package fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import fund.dto.Automation;
import fund.dto.Salary;
import fund.dto.Commitment;
import fund.dto.Files;
import fund.mapper.CommitmentMapper;

@Controller
public class FinanceController {
	@Autowired CommitmentMapper commitmentMapper;

	@RequestMapping(value="/finance/automation.do",method=RequestMethod.GET)
	public String excelRead(Model model) throws Exception{    
		List<Automation> automationList = ReadExcelFileToList.readExcelData("/Users/parkeunsun/Documents/automation_sample_1.xlsx");
		model.addAttribute("automationList",automationList);

		return "finance/automation";
	}

	@RequestMapping(value="/finance/automation.do" ,method = RequestMethod.POST, params="cmd=uploadAutomation")
	public String uploadAutomation(HttpServletRequest request,@RequestParam("file") MultipartFile uploadedFile,RedirectAttributes redirectAttributes,Model model)throws IOException {
		Files files = new Files();
		files.setName(Paths.get(uploadedFile.getOriginalFilename()).getFileName().toString());
		files.setFileData(uploadedFile);

		List<Automation> automationList = ReadExcelFileToList.readExcelData(files.getName());
		model.addAttribute("automationList",automationList);
		return "finance/automation";
	}//서브 폴더 파일업로드
	@RequestMapping(value="/finance/searchCommitment.do", method=RequestMethod.POST)
	public String searchCommitment(Model model, @RequestParam("sponsorNo") String sponsorNo) {
		System.out.println("여");
		model.addAttribute("list", commitmentMapper.selectCommitmentBySponsorNo(sponsorNo));
		return "finance/commitmentList/ajax";
	}
	/**
	@RequestMapping(value="/finance/automation.do", method=RequestMethod.POST, params="cmd=commitmentSearch")
	public String commitmentSearch(@RequestParam("sponsorNo") String sponsorNo,Model model){
		List<Commitment> commitmentList = commitmentMapper.selectCommitmentBySponsorNo(sponsorNo);
		model.addAttribute("commitmentList",commitmentList);
		return "finance/automation";
	}**/

	@RequestMapping(value="/finance/salary.do", method=RequestMethod.GET)
	public String salary(Model model) throws Exception{
		List<Salary> salaryList = ReadExcelSalaryToList.readExcelData("/Users/parkeunsun/Documents/salary_sample_1.xlsx");
		model.addAttribute("salaryList",salaryList);

		return "finance/salary";
	}
}
